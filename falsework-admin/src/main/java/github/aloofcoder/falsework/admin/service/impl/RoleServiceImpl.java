package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.config.BaseContextUtil;
import github.aloofcoder.falsework.admin.dao.RoleDao;
import github.aloofcoder.falsework.admin.pojo.dto.RoleDTO;
import github.aloofcoder.falsework.admin.pojo.dto.RolePageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.RoleEntity;
import github.aloofcoder.falsework.admin.pojo.entity.UserRoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.RoleDetailVO;
import github.aloofcoder.falsework.admin.pojo.vo.RoleListVO;
import github.aloofcoder.falsework.admin.service.IRoleMenuService;
import github.aloofcoder.falsework.admin.service.IRoleService;
import github.aloofcoder.falsework.admin.service.IUserRoleService;
import github.aloofcoder.falsework.common.util.AppException;
import github.aloofcoder.falsework.common.util.ErrorCode;
import github.aloofcoder.falsework.common.util.PageResult;
import org.apache.commons.lang3.StringUtils;
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
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements IRoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public PageResult queryRolePage(RolePageDTO pageDTO) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(pageDTO.getCondition())) {
            queryWrapper.like("role_name", pageDTO.getCondition())
                    .or()
                    .like("role_mark", pageDTO.getCondition());
        }
        queryWrapper.orderByAsc("role_sort");
        IPage<RoleEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                queryWrapper);
        return new PageResult(page);
    }

    @Override
    public RoleDetailVO findRoleDetail(Integer roleId) {
        RoleEntity entity = this.getOne(new QueryWrapper<RoleEntity>().eq("role_id", roleId));
        if (Objects.isNull(entity)) {
            return null;
        }
        RoleDetailVO vo = new RoleDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createRole(RoleDTO roleDTO) {
        // 验证角色名称是否重复
        RoleEntity roleNameEntity = getByRoleName(roleDTO.getRoleName());
        if (Objects.nonNull(roleNameEntity)) {
            // 角色名已存在
            throw new AppException(ErrorCode.ROLE_NAME_REPEAT);
        }
        RoleEntity roleMarkEntity = getByRoleMark(roleDTO.getRoleMark());
        if (Objects.nonNull(roleMarkEntity)) {
            // 角色标记已存在
            throw new AppException(ErrorCode.ROLE_MARK_REPEAT);
        }
        String loginNum = BaseContextUtil.getLoginNum();
        RoleEntity entity = new RoleEntity();
        BeanUtils.copyProperties(roleDTO, entity);
        entity.setCreateBy(loginNum);
        entity.setEditBy(loginNum);
        this.save(entity);
    }

    @Override
    public void updateRole(Integer roleId, RoleDTO roleDTO) {
        // 验证角色名称是否重复
        RoleEntity roleNameEntity = getByRoleName(roleDTO.getRoleName());
        if (Objects.nonNull(roleNameEntity) && !roleId.equals(roleNameEntity.getRoleId())) {
            // 角色名已存在
            throw new AppException(ErrorCode.ROLE_NAME_REPEAT);
        }
        RoleEntity roleMarkEntity = getByRoleMark(roleDTO.getRoleMark());
        if (Objects.nonNull(roleMarkEntity) && !roleId.equals(roleNameEntity.getRoleId())) {
            // 角色标记已存在
            throw new AppException(ErrorCode.ROLE_MARK_REPEAT);
        }
        String loginNum = BaseContextUtil.getLoginNum();
        RoleEntity entity = this.getOne(new QueryWrapper<RoleEntity>().eq("role_id", roleId));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(roleDTO, entity);
        entity.setEditBy(loginNum);
        update(entity, new UpdateWrapper<RoleEntity>().eq("role_id", roleId));
    }

    @Override
    public void deleteRoles(Integer[] roleIds) {
        List<UserRoleEntity> userRoleList = userRoleService.findUserRolesByRoleIds(Arrays.asList(roleIds));
        if (userRoleList.size() > 0) {
            List<Integer> usedRoleIds = userRoleList.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
            QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("role_id", usedRoleIds);
            String roles = this.list(queryWrapper).stream().map(RoleEntity::getRoleName).collect(Collectors.joining());
            throw new AppException(ErrorCode.ROLE_USED.getCode(), String.format("删除角色【%1$s】失败，", roles) + ErrorCode.ROLE_USED.getMsg());
        }
        // 删除角色
        boolean removeRoleFlag = this.removeByIds(Arrays.asList(roleIds));
        if (!removeRoleFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
        // 删除角色分配的菜单/资源
        boolean removeRoleMenuFlag = roleMenuService.removeByRoleIds(Arrays.asList(roleIds));
        if (!removeRoleMenuFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
    }

    @Override
    public List<RoleListVO> findRoleList() {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("role_sort");
        List<RoleEntity> list = this.list();
        List<RoleListVO> roleList = new ArrayList<>();
        list.forEach(item -> {
            RoleListVO vo = new RoleListVO();
            BeanUtils.copyProperties(item, vo);
            roleList.add(vo);
        });
        return roleList;
    }

    @Override
    public void roleAuthMenus(Integer roleId, Integer[] menuIds) {
        RoleEntity entity = this.getOne(new QueryWrapper<RoleEntity>().eq("role_id", roleId));
        if (Objects.isNull(entity)) {
            throw new AppException(ErrorCode.ROLE_ID_INVALID);
        }
        // 删除角色授权的菜单
        boolean removeRoleMenuFlag = roleMenuService.removeByRoleId(roleId);
        if (!removeRoleMenuFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
        // 添加角色与菜单关系
        boolean saveUserRoleFlag = roleMenuService.saveRoleMenus(roleId, menuIds);
        if (!saveUserRoleFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
    }

    private RoleEntity getByRoleMark(String roleMark) {
        QueryWrapper<RoleEntity> roleMarkWrapper = new QueryWrapper<>();
        roleMarkWrapper.eq("role_mark", roleMark);
        RoleEntity entity = getOne(roleMarkWrapper);
        if (Objects.nonNull(entity)) {
            return entity;
        }
        return null;
    }

    private RoleEntity getByRoleName(String roleName) {
        QueryWrapper<RoleEntity> roleNameWrapper = new QueryWrapper<>();
        roleNameWrapper.eq("role_name", roleName);
        RoleEntity entity = getOne(roleNameWrapper);
        if (Objects.nonNull(entity)) {
            return entity;
        }
        return null;
    }
}
