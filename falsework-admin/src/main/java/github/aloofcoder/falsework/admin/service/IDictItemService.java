package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.DictItemEntity;
import github.aloofcoder.falsework.admin.pojo.vo.DictItemDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.DictItemPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.DictItemDTO;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
public interface IDictItemService extends IService<DictItemEntity> {

    /**
    * 分页查询列表
    * @param pageDTO
    * @return
    */
    PageResult queryDictItemPage(DictItemPageDTO pageDTO);

    /**
    * 查询详情
    * @param id
    * @return
    */
    DictItemDetailVO findDictItemDetail(Integer id);

    /**
     * 创建
     * @param dictItemDTO
     *
     */
    void createDictItem(DictItemDTO dictItemDTO);

    /**
     * 修改
     * @param id
     * @param dictItemDTO
     */
    void updateDictItem(Integer id, DictItemDTO dictItemDTO);

    /**
    * 批量删除
    * @param ids
    *
    */
    void deleteDictItems(Integer[] ids);
}
