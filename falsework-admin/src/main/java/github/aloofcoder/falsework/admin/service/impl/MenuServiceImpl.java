package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.MenuDao;
import github.aloofcoder.falsework.admin.pojo.dto.MenuPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.MenuDTO;
import github.aloofcoder.falsework.admin.pojo.entity.MenuEntity;
import github.aloofcoder.falsework.admin.service.IMenuService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.vo.MenuDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * 系统菜单
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements IMenuService {

    @Autowired
    private MenuDao menuDao;

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
}
