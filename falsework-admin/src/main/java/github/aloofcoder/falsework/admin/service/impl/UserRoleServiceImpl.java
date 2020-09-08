package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.UserRoleDao;
import github.aloofcoder.falsework.admin.pojo.dto.UserRoleDTO;
import github.aloofcoder.falsework.admin.pojo.dto.UserRolePageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.RoleEntity;
import github.aloofcoder.falsework.admin.pojo.entity.UserRoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.UserRoleDetailVO;
import github.aloofcoder.falsework.admin.service.IRoleService;
import github.aloofcoder.falsework.admin.service.IUserRoleService;
import github.aloofcoder.falsework.common.util.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements IUserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private IRoleService roleService;

    @Override
    public PageResult queryUserRolePage(UserRolePageDTO pageDTO) {
        IPage<UserRoleEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<UserRoleEntity>());
        return new PageResult(page);
    }

    @Override
    public UserRoleDetailVO findUserRoleDetail(Integer id) {
        UserRoleEntity entity = this.getOne(new QueryWrapper<UserRoleEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        UserRoleDetailVO vo = new UserRoleDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createUserRole(UserRoleDTO userRoleDTO) {
        UserRoleEntity entity = new UserRoleEntity();
        BeanUtils.copyProperties(userRoleDTO, entity);
        this.save(entity);
    }

    @Override
    public void updateUserRole(Integer id, UserRoleDTO userRoleDTO) {
        UserRoleEntity entity = this.getOne(new QueryWrapper<UserRoleEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(userRoleDTO, entity);
        update(entity, new UpdateWrapper<UserRoleEntity>().eq("id", id));
    }

    @Override
    public void deleteUserRoles(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public List<UserRoleEntity> findUserRolesByUserNum(String userNum) {
        return this.list(new QueryWrapper<UserRoleEntity>().eq("user_num", userNum));
    }

    @Override
    public boolean removeByUserNums(String[] userNums) {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<UserRoleEntity>().in("user_num", userNums);
        List<UserRoleEntity> list = this.list(queryWrapper);
        if (list.size() <= 0) {
            return true;
        }
        return this.remove(queryWrapper);
    }

    @Override
    public boolean removeByUserNum(String userNum) {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<UserRoleEntity>();
        queryWrapper.eq("user_num", userNum);
        List<UserRoleEntity> list = this.list(queryWrapper);
        if (list.size() <= 0) {
            return true;
        }
        return this.remove(queryWrapper);
    }

    @Override
    public boolean saveUserRoles(String userNum, Integer[] roleIds) {
        List<UserRoleEntity> userRoleList = new ArrayList<>();
        Arrays.asList(roleIds).stream().forEach(item -> {
            UserRoleEntity entity = new UserRoleEntity();
            entity.setUserNum(userNum);
            entity.setRoleId(item);
            userRoleList.add(entity);
        });
        return this.saveBatch(userRoleList);
    }

    @Override
    public List<String> findUserRoleMarksByUserNum(String userNum) {
        List<UserRoleEntity> userRoles = this.list(new QueryWrapper<UserRoleEntity>().eq("user_num", userNum));
        List<Integer> roleIds = userRoles.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
        if (roleIds.size() <= 0) {
            return new ArrayList<>();
        }
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        List<String> roles = roleService.list(queryWrapper).stream().map(RoleEntity::getRoleMark).collect(Collectors.toList());
        return roles;
    }

    @Override
    public List<UserRoleEntity> findUserRolesByRoleIds(List<Integer> roleIds) {
        if (roleIds.size() == 0) {
            return new ArrayList<>();
        }
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        List<UserRoleEntity> list = this.list(queryWrapper);
        return list;
    }

}
