package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.SettingDao;
import github.aloofcoder.falsework.admin.pojo.dto.SettingPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.SettingDTO;
import github.aloofcoder.falsework.admin.pojo.entity.SettingEntity;
import github.aloofcoder.falsework.admin.service.ISettingService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.vo.SettingDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * 系统全局设置表
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingDao, SettingEntity> implements ISettingService {

    @Autowired
    private SettingDao settingDao;

    @Override
    public PageResult querySettingPage(SettingPageDTO pageDTO) {
        IPage<SettingEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<SettingEntity>());
        return new PageResult(page);
    }

    @Override
    public SettingDetailVO findSettingDetail(Integer id) {
        SettingEntity entity = this.getOne(new QueryWrapper<SettingEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        SettingDetailVO vo = new SettingDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createSetting(SettingDTO settingDTO) {
        SettingEntity entity = new SettingEntity();
        BeanUtils.copyProperties(settingDTO, entity);
        this.save(entity);
    }

    @Override
    public void updateSetting(Integer id, SettingDTO settingDTO) {
        SettingEntity entity = this.getOne(new QueryWrapper<SettingEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(settingDTO, entity);
        update(entity, new UpdateWrapper<SettingEntity>().eq("id", id));
    }

    @Override
    public void deleteSettings(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
