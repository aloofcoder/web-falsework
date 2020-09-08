package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.config.BaseContextUtil;
import github.aloofcoder.falsework.admin.dao.MenuDao;
import github.aloofcoder.falsework.admin.pojo.dto.MenuDTO;
import github.aloofcoder.falsework.admin.pojo.dto.MenuPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.MenuTreeDTO;
import github.aloofcoder.falsework.admin.pojo.entity.MenuEntity;
import github.aloofcoder.falsework.admin.pojo.entity.RoleMenuEntity;
import github.aloofcoder.falsework.admin.pojo.vo.MenuAuthListVO;
import github.aloofcoder.falsework.admin.pojo.vo.MenuDetailVO;
import github.aloofcoder.falsework.admin.pojo.vo.MenuListVO;
import github.aloofcoder.falsework.admin.service.IMenuService;
import github.aloofcoder.falsework.admin.service.IRoleMenuService;
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
 * 系统菜单
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 14:48:53
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements IMenuService {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public PageResult queryMenuPage(MenuPageDTO pageDTO) {
        IPage<MenuEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<MenuEntity>());
        return new PageResult(page);
    }

    @Override
    public MenuDetailVO findMenuDetail(Integer id) {
        MenuEntity entity = this.getOne(new QueryWrapper<MenuEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        MenuDetailVO vo = new MenuDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createMenu(MenuDTO menuDTO) {
        MenuEntity menuNameEntity = findByMenuName(menuDTO.getMenuName());
        if (Objects.nonNull(menuNameEntity)) {
            throw new AppException(ErrorCode.MENU_NAME_REPEAT);
        }
        MenuEntity menuPathEntity = findByMenuPath(menuDTO.getMenuPath());
        if (Objects.nonNull(menuPathEntity)) {
            throw new AppException(ErrorCode.MENU_PATH_REPEAT);
        }
        String loginNum = BaseContextUtil.getLoginNum();
        MenuEntity entity = new MenuEntity();
        BeanUtils.copyProperties(menuDTO, entity);
        entity.setCreateBy(loginNum);
        entity.setEditBy(loginNum);
        this.save(entity);
    }

    @Override
    public void updateMenu(Integer id, MenuDTO menuDTO) {
        MenuEntity menuNameEntity = findByMenuName(menuDTO.getMenuName());
        if (Objects.nonNull(menuNameEntity) && !id.equals(menuNameEntity.getId())) {
            throw new AppException(ErrorCode.MENU_NAME_REPEAT);
        }
        MenuEntity menuPathEntity = findByMenuPath(menuDTO.getMenuPath());
        if (Objects.nonNull(menuPathEntity) && !id.equals(menuNameEntity.getId())) {
            throw new AppException(ErrorCode.MENU_PATH_REPEAT);
        }
        String loginNum = BaseContextUtil.getLoginNum();
        MenuEntity entity = this.getOne(new QueryWrapper<MenuEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new AppException(ErrorCode.MENU_ID_INVALID);
        }
        BeanUtils.copyProperties(menuDTO, entity);
        entity.setEditBy(loginNum);
        update(entity, new UpdateWrapper<MenuEntity>().eq("id", id));
    }

    @Override
    public void deleteMenus(Integer[] ids) {
        List<RoleMenuEntity> roleMenuList = roleMenuService.findRoleMenuByMenuIds(Arrays.asList(ids));
        if (roleMenuList.size() > 0) {
            List<Integer> menuIds = roleMenuList.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
            String menuNames = this.list(new QueryWrapper<MenuEntity>().eq("id", menuIds))
                    .stream().map(MenuEntity::getMenuName).collect(Collectors.joining());
            throw new AppException(ErrorCode.MENU_USED.getCode(), String.format("删除菜单【%1$s】失败，", menuNames) + ErrorCode.MENU_USED.getMsg());
        }
        boolean removeMenuFlag = this.removeByIds(Arrays.asList(ids));
        if (!removeMenuFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
    }

    @Override
    public List<MenuEntity> findMenuTree(MenuTreeDTO menuTreeDTO) {
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(menuTreeDTO.getCondition())) {
            queryWrapper.like("menu_name", menuTreeDTO.getCondition())
                    .or().like("menu_path", menuTreeDTO.getCondition());
        }
        List<MenuEntity> menuList = this.list(queryWrapper);
        List<MenuEntity> tree = new ArrayList<>();

        menuList.forEach(item -> {
            if (0 == item.getParentId()) {
                tree.add(item);
            }
            menuList.forEach(menu -> {
                if (item.getId().equals(menu.getParentId())) {
                    if (Objects.isNull(item.getChildren())) {
                        List<MenuEntity> list = new ArrayList<>();
                        list.add(menu);
                        item.setChildren(list);
                    } else {
                        item.getChildren().add(menu);
                    }
                }
            });
        });
        return tree;
    }

    @Override
    public List<MenuEntity> findAuth() {
        List<Integer> authMenuIds = roleMenuService.findRoleAuthMenuIds();
        if (authMenuIds.size() == 0) {
            return new ArrayList<>();
        }
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_class", 3)
                .eq("status", 1)
                .in("id", authMenuIds);
        List<MenuEntity> menuList = this.list(queryWrapper);
        return menuList;
    }

    @Override
    public List<MenuListVO> findMenuList(boolean hasBtn) {
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        if (!hasBtn) {
            queryWrapper.ne("menu_class", 3);
        }
        List<MenuListVO> menuList = this.list(queryWrapper).stream().map(item -> {
            MenuListVO vo = new MenuListVO();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        List<MenuListVO> tree = new ArrayList<>();
        menuList.forEach(item -> {
            if (0 == item.getParentId()) {
                tree.add(item);
            }
            menuList.forEach(menu -> {
                if (item.getId().equals(menu.getParentId())) {
                    if (Objects.isNull(item.getChildren())) {
                        List<MenuListVO> list = new ArrayList<>();
                        list.add(menu);
                        item.setChildren(list);
                    } else {
                        item.getChildren().add(menu);
                    }
                }
            });
        });

        return tree;
    }

    @Override
    public List<MenuAuthListVO> findAuthMenu() {
        List<Integer> authMenuIds = roleMenuService.findRoleAuthMenuIds();
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("menu_class", 3)
                .eq("status", 1)
                .in("id", authMenuIds);
        List<MenuAuthListVO> menuList = this.list(queryWrapper).stream().map(item -> {
            MenuAuthListVO vo = new MenuAuthListVO();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        return menuList;
    }

    @Override
    public List<MenuListVO> findRoleMenuListByRoleId(Integer roleId) {
        QueryWrapper<RoleMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<Integer> menuIds = roleMenuService.list(queryWrapper).stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
        if (menuIds.size() <= 0) {
            return new ArrayList<>();
        }
        QueryWrapper<MenuEntity> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.in("id", menuIds).eq("status", 1);
        List<MenuListVO> roleMenu = this.list(menuQueryWrapper).stream().map(item -> {
            MenuListVO vo = new MenuListVO();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        return roleMenu;
    }

    private MenuEntity findByMenuName(String menuName) {
        QueryWrapper<MenuEntity> menuNameWrapper = new QueryWrapper<>();
        menuNameWrapper.eq("menu_name", menuName);
        MenuEntity entity = getOne(menuNameWrapper);
        if (Objects.nonNull(entity)) {
            return entity;
        }
        return null;
    }

    private MenuEntity findByMenuPath(String menuPath) {
        QueryWrapper<MenuEntity> menuPathWrapper = new QueryWrapper<>();
        menuPathWrapper.eq("menu_path", menuPath);
        MenuEntity entity = getOne(menuPathWrapper);
        if (Objects.nonNull(entity)) {
            return entity;
        }
        return null;
    }
}
