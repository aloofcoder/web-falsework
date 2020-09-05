package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.RoleDao;
import github.aloofcoder.falsework.admin.pojo.dto.RoleDTO;
import github.aloofcoder.falsework.admin.pojo.dto.RolePageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.RoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.RoleDetailVO;
import github.aloofcoder.falsework.admin.pojo.vo.RoleListVO;
import github.aloofcoder.falsework.admin.service.IRoleService;
import github.aloofcoder.falsework.common.util.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements IRoleService {

    @Autowired
    private RoleDao roleDao;

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
        RoleEntity entity = new RoleEntity();
        BeanUtils.copyProperties(roleDTO, entity);
        entity.setCreateBy("1");
        entity.setEditBy("1");
        this.save(entity);
    }

    @Override
    public void updateRole(Integer roleId, RoleDTO roleDTO) {
        RoleEntity entity = this.getOne(new QueryWrapper<RoleEntity>().eq("role_id", roleId));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(roleDTO, entity);
        update(entity, new UpdateWrapper<RoleEntity>().eq("role_id", roleId));
    }

    @Override
    public void deleteRoles(Integer[] roleIds) {
        this.removeByIds(Arrays.asList(roleIds));
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
}
