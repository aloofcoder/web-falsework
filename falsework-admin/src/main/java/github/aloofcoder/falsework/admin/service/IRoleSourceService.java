package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.RoleSourceEntity;
import github.aloofcoder.falsework.admin.pojo.vo.RoleSourceDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.RoleSourcePageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.RoleSourceDTO;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
public interface IRoleSourceService extends IService<RoleSourceEntity> {

    /**
    * 分页查询列表
    * @param pageDTO
    * @return
    */
    PageResult queryRoleSourcePage(RoleSourcePageDTO pageDTO);

    /**
    * 查询详情
    * @param id
    * @return
    */
    RoleSourceDetailVO findRoleSourceDetail(Integer id);

    /**
     * 创建
     * @param roleSourceDTO
     *
     */
    void createRoleSource(RoleSourceDTO roleSourceDTO);

    /**
     * 修改
     * @param id
     * @param roleSourceDTO
     */
    void updateRoleSource(Integer id, RoleSourceDTO roleSourceDTO);

    /**
    * 批量删除
    * @param ids
    *
    */
    void deleteRoleSources(Integer[] ids);
}
