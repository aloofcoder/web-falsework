package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.config.BaseContextUtil;
import github.aloofcoder.falsework.admin.dao.RoleMenuDao;
import github.aloofcoder.falsework.admin.pojo.dto.RoleMenuDTO;
import github.aloofcoder.falsework.admin.pojo.dto.RoleMenuPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.RoleMenuEntity;
import github.aloofcoder.falsework.admin.pojo.entity.UserRoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.RoleMenuDetailVO;
import github.aloofcoder.falsework.admin.service.IRoleMenuService;
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
 * @date 2020-08-14 01:30:54
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements IRoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public PageResult queryRoleMenuPage(RoleMenuPageDTO pageDTO) {
        IPage<RoleMenuEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<RoleMenuEntity>());
        return new PageResult(page);
    }

    @Override
    public RoleMenuDetailVO findRoleMenuDetail(Integer id) {
        RoleMenuEntity entity = this.getOne(new QueryWrapper<RoleMenuEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        RoleMenuDetailVO vo = new RoleMenuDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createRoleMenu(RoleMenuDTO roleMenuDTO) {
        RoleMenuEntity entity = new RoleMenuEntity();
        BeanUtils.copyProperties(roleMenuDTO, entity);
        this.save(entity);
    }

    @Override
    public void updateRoleMenu(Integer id, RoleMenuDTO roleMenuDTO) {
        RoleMenuEntity entity = this.getOne(new QueryWrapper<RoleMenuEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(roleMenuDTO, entity);
        update(entity, new UpdateWrapper<RoleMenuEntity>().eq("id", id));
    }

    @Override
    public void deleteRoleMenus(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public List<Integer> findRoleAuthMenuIds() {
        String loginNum = BaseContextUtil.getLoginNum();
        List<String> loginRoles = BaseContextUtil.getLoginRoles();
        QueryWrapper<RoleMenuEntity> queryWrapper = new QueryWrapper<>();
        List<UserRoleEntity> userRoleList = userRoleService.findUserRolesByUserNum(loginNum);
        List<Integer> roleIds = userRoleList.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
        if (roleIds.size() <= 0) {
            return new ArrayList<>();
        }
        queryWrapper.in("role_id", roleIds);
        List<Integer> menuIds = this.list(queryWrapper).stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
        return menuIds;
    }

    @Override
    public boolean removeByRoleId(Integer roleId) {
        QueryWrapper<RoleMenuEntity> queryWrapper = new QueryWrapper<RoleMenuEntity>();
        queryWrapper.eq("role_id", roleId);
        List<RoleMenuEntity> list = this.list(queryWrapper);
        if (list.size() <= 0) {
            return true;
        }
        return this.remove(queryWrapper);
    }

    @Override
    public boolean saveRoleMenus(Integer roleId, Integer[] menuIds) {
        List<RoleMenuEntity> roleMenuList = new ArrayList<>();
        Arrays.asList(menuIds).stream().forEach(item -> {
            RoleMenuEntity entity = new RoleMenuEntity();
            entity.setRoleId(roleId);
            entity.setMenuId(item);
            roleMenuList.add(entity);
        });
        return this.saveBatch(roleMenuList);
    }

    @Override
    public boolean removeByRoleIds(List<Integer> roleIds) {
        QueryWrapper<RoleMenuEntity> queryWrapper = new QueryWrapper<RoleMenuEntity>();
        queryWrapper.in("role_id", roleIds);
        List<RoleMenuEntity> list = this.list(queryWrapper);
        if (list.size() <= 0) {
            return true;
        }
        return this.remove(queryWrapper);
    }

    @Override
    public List<RoleMenuEntity> findRoleMenuByMenuIds(List<Integer> menuIds) {
        List<RoleMenuEntity> list = this.list(new QueryWrapper<RoleMenuEntity>().in("menu_id", menuIds));
        return list;
    }
}
