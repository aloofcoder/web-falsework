package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.SourceDao;
import github.aloofcoder.falsework.admin.pojo.dto.SourcePageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.SourceDTO;
import github.aloofcoder.falsework.admin.pojo.entity.SourceEntity;
import github.aloofcoder.falsework.admin.service.ISourceService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.vo.SourceDetailVO;
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
public class SourceServiceImpl extends ServiceImpl<SourceDao, SourceEntity> implements ISourceService {

    @Autowired
    private SourceDao sourceDao;

    @Override
    public PageResult querySourcePage(SourcePageDTO pageDTO) {
        IPage<SourceEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<SourceEntity>());
        return new PageResult(page);
    }

    @Override
    public SourceDetailVO findSourceDetail(Integer id) {
        SourceEntity entity = this.getOne(new QueryWrapper<SourceEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        SourceDetailVO vo = new SourceDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createSource(SourceDTO sourceDTO) {
        SourceEntity entity = new SourceEntity();
        BeanUtils.copyProperties(sourceDTO, entity);
        this.save(entity);
    }

    @Override
    public void updateSource(Integer id, SourceDTO sourceDTO) {
        SourceEntity entity = this.getOne(new QueryWrapper<SourceEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(sourceDTO, entity);
        update(entity, new UpdateWrapper<SourceEntity>().eq("id", id));
    }

    @Override
    public void deleteSources(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
