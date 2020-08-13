package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.RoleMenuDao;
import github.aloofcoder.falsework.admin.pojo.dto.RoleMenuPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.RoleMenuDTO;
import github.aloofcoder.falsework.admin.pojo.entity.RoleMenuEntity;
import github.aloofcoder.falsework.admin.service.IRoleMenuService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.vo.RoleMenuDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements IRoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

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
}
