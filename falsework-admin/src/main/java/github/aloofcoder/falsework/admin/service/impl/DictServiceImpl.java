package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.DictDao;
import github.aloofcoder.falsework.admin.pojo.dto.DictPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.DictDTO;
import github.aloofcoder.falsework.admin.pojo.entity.DictEntity;
import github.aloofcoder.falsework.admin.service.IDictService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.vo.DictDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * 数据字典
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictDao, DictEntity> implements IDictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public PageResult queryDictPage(DictPageDTO pageDTO) {
        IPage<DictEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<DictEntity>());
        return new PageResult(page);
    }

    @Override
    public DictDetailVO findDictDetail(Integer id) {
        DictEntity entity = this.getOne(new QueryWrapper<DictEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        DictDetailVO vo = new DictDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createDict(DictDTO dictDTO) {
        DictEntity entity = new DictEntity();
        BeanUtils.copyProperties(dictDTO, entity);
        this.save(entity);
    }

    @Override
    public void updateDict(Integer id, DictDTO dictDTO) {
        DictEntity entity = this.getOne(new QueryWrapper<DictEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(dictDTO, entity);
        update(entity, new UpdateWrapper<DictEntity>().eq("id", id));
    }

    @Override
    public void deleteDicts(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
