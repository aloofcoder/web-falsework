package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.DictItemDao;
import github.aloofcoder.falsework.admin.pojo.dto.DictItemDTO;
import github.aloofcoder.falsework.admin.pojo.dto.DictItemPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.DictItemEntity;
import github.aloofcoder.falsework.admin.pojo.vo.DictItemDetailVO;
import github.aloofcoder.falsework.admin.service.IDictItemService;
import github.aloofcoder.falsework.common.util.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemDao, DictItemEntity> implements IDictItemService {

    @Autowired
    private DictItemDao dictItemDao;

    @Override
    public PageResult queryDictItemPage(DictItemPageDTO pageDTO) {
        IPage<DictItemEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<DictItemEntity>().eq("dict_id", pageDTO.getDictId()));
        return new PageResult(page);
    }

    @Override
    public DictItemDetailVO findDictItemDetail(Integer id) {
        DictItemEntity entity = this.getOne(new QueryWrapper<DictItemEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        DictItemDetailVO vo = new DictItemDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createDictItem(DictItemDTO dictItemDTO) {
        DictItemEntity entity = new DictItemEntity();
        BeanUtils.copyProperties(dictItemDTO, entity);
        this.save(entity);
    }

    @Override
    public void updateDictItem(Integer id, DictItemDTO dictItemDTO) {
        DictItemEntity entity = this.getOne(new QueryWrapper<DictItemEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(dictItemDTO, entity);
        update(entity, new UpdateWrapper<DictItemEntity>().eq("id", id));
    }

    @Override
    public void deleteDictItems(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
