package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.RoleSourceDao;
import github.aloofcoder.falsework.admin.pojo.dto.RoleSourcePageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.RoleSourceDTO;
import github.aloofcoder.falsework.admin.pojo.entity.RoleSourceEntity;
import github.aloofcoder.falsework.admin.service.IRoleSourceService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.vo.RoleSourceDetailVO;
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
public class RoleSourceServiceImpl extends ServiceImpl<RoleSourceDao, RoleSourceEntity> implements IRoleSourceService {

    @Autowired
    private RoleSourceDao roleSourceDao;

    @Override
    public PageResult queryRoleSourcePage(RoleSourcePageDTO pageDTO) {
        IPage<RoleSourceEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<RoleSourceEntity>());
        return new PageResult(page);
    }

    @Override
    public RoleSourceDetailVO findRoleSourceDetail(Integer id) {
        RoleSourceEntity entity = this.getOne(new QueryWrapper<RoleSourceEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        RoleSourceDetailVO vo = new RoleSourceDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createRoleSource(RoleSourceDTO roleSourceDTO) {
        RoleSourceEntity entity = new RoleSourceEntity();
        BeanUtils.copyProperties(roleSourceDTO, entity);
        this.save(entity);
    }

    @Override
    public void updateRoleSource(Integer id, RoleSourceDTO roleSourceDTO) {
        RoleSourceEntity entity = this.getOne(new QueryWrapper<RoleSourceEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(roleSourceDTO, entity);
        update(entity, new UpdateWrapper<RoleSourceEntity>().eq("id", id));
    }

    @Override
    public void deleteRoleSources(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
