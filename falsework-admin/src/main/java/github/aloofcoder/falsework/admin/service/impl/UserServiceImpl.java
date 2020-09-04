package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.UserDao;
import github.aloofcoder.falsework.admin.pojo.dto.UserDTO;
import github.aloofcoder.falsework.admin.pojo.dto.UserPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.OrgEntity;
import github.aloofcoder.falsework.admin.pojo.entity.OrgUserEntity;
import github.aloofcoder.falsework.admin.pojo.entity.UserEntity;
import github.aloofcoder.falsework.admin.pojo.entity.UserRoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.UserDetailVO;
import github.aloofcoder.falsework.admin.pojo.vo.UserPageVO;
import github.aloofcoder.falsework.admin.service.IOrgService;
import github.aloofcoder.falsework.admin.service.IOrgUserService;
import github.aloofcoder.falsework.admin.service.IUserRoleService;
import github.aloofcoder.falsework.admin.service.IUserService;
import github.aloofcoder.falsework.common.util.AppException;
import github.aloofcoder.falsework.common.util.PageResult;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        Integer orgId = userDTO.getOrgId();
        OrgEntity orgEntity = orgService.findOrgByOrgId(orgId);
        if (Objects.isNull(orgEntity)) {
            throw new AppException("无效的组织");
        }
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userDTO, entity);
        String userNum = RandomUtils.nextLong(1000000000L, 9999999999L) + "";
        entity.setUserNum(userNum);
        entity.setCreateBy("1");
        entity.setEditBy("1");
        boolean addUserFlag = this.save(entity);
        if (!addUserFlag) {
            throw new AppException("添加用户失败，请重试");
        }
        // 添加用户与组织关系
        boolean addOrgUserFlag = orgUserService.saveOrgUser(entity.getUserNum(), orgId);
        if (!addOrgUserFlag) {
            throw new AppException("添加用户失败，请重试");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(String userNum, UserDTO userDTO) {
        Integer orgId = userDTO.getOrgId();
        OrgEntity orgEntity = orgService.findOrgByOrgId(orgId);
        if (Objects.isNull(orgEntity)) {
            throw new AppException("无效的组织");
        }
        UserEntity entity = this.getOne(new QueryWrapper<UserEntity>().eq("user_num", userNum));
        if (Objects.isNull(entity)) {
            throw new AppException("修改用户失败，无效的用户编号");
        }
        BeanUtils.copyProperties(userDTO, entity);
        boolean editUserFlag = update(entity, new UpdateWrapper<UserEntity>().eq("user_num", userNum));
        if (!editUserFlag) {
            throw new AppException("修改用户失败，请重试");
        }
        // 移除用户原有组织
        boolean removeUserOrgFlag = orgUserService.removeByUserNum(userNum);
        if (!removeUserOrgFlag) {
            throw new AppException("修改用户失败，请重试");
        }
        // 添加用户与组织关系
        boolean addOrgUserFlag = orgUserService.saveOrgUser(entity.getUserNum(), orgId);
        if (!addOrgUserFlag) {
            throw new AppException("添加用户失败，请重试");
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
            throw new AppException("管理员账号不能被删除");
        }
        boolean removeUserFlag = this.remove(queryWrapper);
        if (!removeUserFlag) {
            throw new AppException("删除用户失败，请重试");
        }
        boolean removeOrgUserFlag = orgUserService.removeByUserNums(userNums);
        if (!removeOrgUserFlag) {
            throw new AppException("删除用户失败，请重试");
        }
        boolean removeUserRoleFlag = userRoleService.removeByUserNums(userNums);
        if (!removeUserRoleFlag) {
            throw new AppException("删除用户失败，请重试");
        }

    }

    @Override
    public List<Integer> findUserRoles(String userNum) {
        List<UserRoleEntity> roleUsers = userRoleService.findUserRolesByUserNums(userNum);
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
            throw new AppException("分配用户角色失败，无效的用户编号");
        }
        // 删除用户角色
        boolean removeUserRoleFlag = userRoleService.removeByUserNum(userNum);
        if (!removeUserRoleFlag) {
            throw new AppException("分配用户角色失败，请重试");
        }
        // 添加用户与角色关系
        boolean saveUserRoleFlag = userRoleService.saveUserRoles(userNum, roleIds);
        if (!saveUserRoleFlag) {
            throw new AppException("分配用户角色失败，请重试");
        }
    }
}
