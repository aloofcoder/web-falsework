package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.SourceEntity;
import github.aloofcoder.falsework.admin.pojo.vo.SourceDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.SourcePageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.SourceDTO;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
public interface ISourceService extends IService<SourceEntity> {

    /**
    * 分页查询列表
    * @param pageDTO
    * @return
    */
    PageResult querySourcePage(SourcePageDTO pageDTO);

    /**
    * 查询详情
    * @param id
    * @return
    */
    SourceDetailVO findSourceDetail(Integer id);

    /**
     * 创建
     * @param sourceDTO
     *
     */
    void createSource(SourceDTO sourceDTO);

    /**
     * 修改
     * @param id
     * @param sourceDTO
     */
    void updateSource(Integer id, SourceDTO sourceDTO);

    /**
    * 批量删除
    * @param ids
    *
    */
    void deleteSources(Integer[] ids);
}
