package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.SettingEntity;
import github.aloofcoder.falsework.admin.pojo.vo.SettingDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.SettingPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.SettingDTO;

/**
 * 系统全局设置表
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
public interface ISettingService extends IService<SettingEntity> {

    /**
    * 分页查询系统全局设置表列表
    * @param pageDTO
    * @return
    */
    PageResult querySettingPage(SettingPageDTO pageDTO);

    /**
    * 查询系统全局设置表详情
    * @param id
    * @return
    */
    SettingDetailVO findSettingDetail(Integer id);

    /**
     * 创建系统全局设置表
     * @param settingDTO
     *
     */
    void createSetting(SettingDTO settingDTO);

    /**
     * 修改系统全局设置表
     * @param id
     * @param settingDTO
     */
    void updateSetting(Integer id, SettingDTO settingDTO);

    /**
    * 批量删除系统全局设置表
    * @param ids
    *
    */
    void deleteSettings(Integer[] ids);
}
