package github.aloofcoder.falsework.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import github.aloofcoder.falsework.admin.pojo.entity.SettingEntity;

/**
 * 系统全局设置表
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Repository
public interface SettingDao extends BaseMapper<SettingEntity> {

}
