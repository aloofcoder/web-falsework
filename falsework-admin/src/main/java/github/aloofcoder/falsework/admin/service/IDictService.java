package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.DictEntity;
import github.aloofcoder.falsework.admin.pojo.vo.DictDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.DictPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.DictDTO;

/**
 * 数据字典
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
public interface IDictService extends IService<DictEntity> {

    /**
    * 分页查询数据字典列表
    * @param pageDTO
    * @return
    */
    PageResult queryDictPage(DictPageDTO pageDTO);

    /**
    * 查询数据字典详情
    * @param id
    * @return
    */
    DictDetailVO findDictDetail(Integer id);

    /**
     * 创建数据字典
     * @param dictDTO
     *
     */
    void createDict(DictDTO dictDTO);

    /**
     * 修改数据字典
     * @param id
     * @param dictDTO
     */
    void updateDict(Integer id, DictDTO dictDTO);

    /**
    * 批量删除数据字典
    * @param ids
    *
    */
    void deleteDicts(Integer[] ids);
}
