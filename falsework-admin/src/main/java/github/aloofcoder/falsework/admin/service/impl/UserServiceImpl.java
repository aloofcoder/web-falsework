package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.config.BaseContextUtil;
import github.aloofcoder.falsework.admin.dao.UserDao;
import github.aloofcoder.falsework.admin.pojo.bo.UserAuthBO;
import github.aloofcoder.falsework.admin.pojo.dto.UserDTO;
import github.aloofcoder.falsework.admin.pojo.dto.UserPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.OrgEntity;
import github.aloofcoder.falsework.admin.pojo.entity.OrgUserEntity;
import github.aloofcoder.falsework.admin.pojo.entity.UserEntity;
import github.aloofcoder.falsework.admin.pojo.entity.UserRoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.UserDetailVO;
import github.aloofcoder.falsework.admin.pojo.vo.UserPageVO;
import github.aloofcoder.falsework.admin.service.*;
import github.aloofcoder.falsework.common.util.AppException;
import github.aloofcoder.falsework.common.util.ErrorCode;
import github.aloofcoder.falsework.common.util.PageResult;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements IUserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IOrgService orgService;
    @Autowired
    private IOrgUserService orgUserService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageResult queryUserPage(UserPageDTO pageDTO) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(pageDTO.getCondition())) {
            wrapper.like("user_name", pageDTO.getCondition())
                    .or().like("user_email", pageDTO.getCondition())
                    .or().like("login_name", pageDTO.getCondition())
                    .or().like("phone_num", pageDTO.getCondition());
        }
        if (StringUtils.isNotBlank(pageDTO.getField()) && pageDTO.getDesc() == 1) {
            wrapper.orderByDesc(pageDTO.getField());
        } else if (StringUtils.isNotBlank(pageDTO.getField()) && pageDTO.getDesc() == 0) {
            wrapper.orderByAsc(pageDTO.getField());
        }
        IPage<UserEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                wrapper);
        List<UserPageVO> userList = new ArrayList<>();
        page.getRecords().stream().forEach(item -> {
            UserPageVO vo = new UserPageVO();
            BeanUtils.copyProperties(item, vo);
            userList.add(vo);
        });
        PageResult pageResult = new PageResult(page);
        pageResult.setList(userList);
        return pageResult;
    }

    @Override
    public UserDetailVO findUserDetail(String userNum) {
        UserEntity entity = this.getOne(new QueryWrapper<UserEntity>().eq("user_num", userNum));
        if (Objects.isNull(entity)) {
            return null;
        }
        UserDetailVO vo = new UserDetailVO();
        BeanUtils.copyProperties(entity, vo);
        OrgUserEntity orgUserEntity = orgUserService.findOrgUserByUserNum(userNum);
        if (Objects.nonNull(orgUserEntity)) {
            Integer orgId = orgUserEntity.getOrgId();
            vo.setOrgId(orgId);
        }
        return vo;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createUser(UserDTO userDTO) {
        UserEntity loginNameExists = checkLoginNameSysExists(userDTO.getLoginName());
        if (Objects.nonNull(loginNameExists)) {
            // 登录名已存在
            throw new AppException(ErrorCode.USER_NAME_REPEAT);
        }
        String loginNum = BaseContextUtil.getLoginNum();
        Integer orgId = userDTO.getOrgId();
        OrgEntity orgEntity = orgService.findOrgByOrgId(orgId);
        if (Objects.isNull(orgEntity)) {
            throw new AppException(ErrorCode.ORG_ID_INVALID);
        }
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userDTO, entity);
        String userNum = RandomUtils.nextLong(1000000000L, 9999999999L) + "";
        entity.setUserNum(userNum);
        entity.setLoginPwd(passwordEncoder.encode(entity.getLoginPwd()));
        entity.setCreateBy(loginNum);
        entity.setEditBy(loginNum);
        boolean addUserFlag = this.save(entity);
        if (!addUserFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
        // 添加用户与组织关系
        boolean addOrgUserFlag = orgUserService.saveOrgUser(entity.getUserNum(), orgId);
        if (!addOrgUserFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(String userNum, UserDTO userDTO) {
        String loginNum = BaseContextUtil.getLoginNum();
        Integer orgId = userDTO.getOrgId();
        OrgEntity orgEntity = orgService.findOrgByOrgId(orgId);
        if (Objects.isNull(orgEntity)) {
            throw new AppException(ErrorCode.ORG_ID_INVALID);
        }
        UserEntity loginNameExists = checkLoginNameSysExists(userDTO.getLoginName());
        if (Objects.nonNull(loginNameExists) && !userNum.equals(loginNameExists.getUserNum())) {
            // 系统已存在该登录名
            throw new AppException(ErrorCode.USER_NAME_REPEAT);
        }
        UserEntity entity = this.getOne(new QueryWrapper<UserEntity>().eq("user_num", userNum));
        if (Objects.isNull(entity)) {
            throw new AppException(ErrorCode.USER_NUM_ERR);
        }
        BeanUtils.copyProperties(userDTO, entity);
        entity.setEditBy(loginNum);
        boolean editUserFlag = update(entity, new UpdateWrapper<UserEntity>().eq("user_num", userNum));
        if (!editUserFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
        // 移除用户原有组织
        boolean removeUserOrgFlag = orgUserService.removeByUserNum(userNum);
        if (!removeUserOrgFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
        // 添加用户与组织关系
        boolean addOrgUserFlag = orgUserService.saveOrgUser(entity.getUserNum(), orgId);
        if (!addOrgUserFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
    }

    public static final String ADMIN_LOGIN_NAME = "admin";

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUsers(String[] userNums) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_num", userNums);
        List<UserEntity> list = this.list(queryWrapper);
        long adminCount = list.stream().filter(item -> item.getLoginName().equals(ADMIN_LOGIN_NAME)).count();
        if (adminCount > 0) {
            throw new AppException(ErrorCode.USER_DEL_ADMIN_ERR);
        }
        boolean removeUserFlag = this.remove(queryWrapper);
        if (!removeUserFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
        boolean removeOrgUserFlag = orgUserService.removeByUserNums(userNums);
        if (!removeOrgUserFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
        boolean removeUserRoleFlag = userRoleService.removeByUserNums(userNums);
        if (!removeUserRoleFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }

    }

    @Override
    public List<Integer> findUserRoles(String userNum) {
        List<UserRoleEntity> roleUsers = userRoleService.findUserRolesByUserNum(userNum);
        if (Objects.isNull(roleUsers) || roleUsers.size() <= 0) {
            return null;
        }
        List<Integer> roleIds = roleUsers.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
        return roleIds;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void userRoleAssign(String userNum, Integer[] roleIds) {
        UserEntity entity = this.getOne(new QueryWrapper<UserEntity>().eq("user_num", userNum));
        if (Objects.isNull(entity)) {
            throw new AppException(ErrorCode.USER_NUM_ERR);
        }
        // 删除用户角色
        boolean removeUserRoleFlag = userRoleService.removeByUserNum(userNum);
        if (!removeUserRoleFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
        // 添加用户与角色关系
        boolean saveUserRoleFlag = userRoleService.saveUserRoles(userNum, roleIds);
        if (!saveUserRoleFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
    }

    @Override
    public UserAuthBO findUserByLoginName(String loginName) {
        UserEntity entity = this.getOne(new QueryWrapper<UserEntity>().eq("login_name", loginName));
        if (Objects.isNull(entity)) {
            return null;
        }
        UserAuthBO bo = new UserAuthBO();
        bo.setUserNum(entity.getUserNum());
        bo.setLoginName(entity.getLoginName());
        bo.setLoginPwd(entity.getLoginPwd());
        List<String> roles = userRoleService.findUserRoleMarksByUserNum(entity.getUserNum());
        bo.setRoles(roles);
        return bo;
    }

    /**
     * 检查登录名是否存在
     *
     * @param loginName
     * @return
     */
    private UserEntity checkLoginNameSysExists(String loginName) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", loginName);
        UserEntity lastUser = getOne(wrapper);
        if (Objects.nonNull(lastUser)) {
            return lastUser;
        }
        return null;
    }
}
