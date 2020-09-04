package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.MenuDao;
import github.aloofcoder.falsework.admin.pojo.dto.MenuDTO;
import github.aloofcoder.falsework.admin.pojo.dto.MenuPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.MenuTreeDTO;
import github.aloofcoder.falsework.admin.pojo.entity.MenuEntity;
import github.aloofcoder.falsework.admin.pojo.vo.MenuAuthListVO;
import github.aloofcoder.falsework.admin.pojo.vo.MenuDetailVO;
import github.aloofcoder.falsework.admin.pojo.vo.MenuListVO;
import github.aloofcoder.falsework.admin.service.IMenuService;
import github.aloofcoder.falsework.admin.service.IRoleMenuService;
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
        MenuEntity entity = new MenuEntity();
        BeanUtils.copyProperties(menuDTO, entity);
        entity.setCreateBy("1");
        entity.setEditBy("1");
        this.save(entity);
    }

    @Override
    public void updateMenu(Integer id, MenuDTO menuDTO) {
        MenuEntity entity = this.getOne(new QueryWrapper<MenuEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(menuDTO, entity);
        update(entity, new UpdateWrapper<MenuEntity>().eq("id", id));
    }

    @Override
    public void deleteMenus(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
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
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_class", 3).eq("status", 1).in("id", authMenuIds);
        List<MenuEntity> menuList = this.list(queryWrapper);
        return menuList;
    }

    @Override
    public List<MenuListVO> findMenuList() {
        QueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("menu_class", 3);
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
}
