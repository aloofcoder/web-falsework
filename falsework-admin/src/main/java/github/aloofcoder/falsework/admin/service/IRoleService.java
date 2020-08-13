package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.RoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.RoleDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.RolePageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.RoleDTO;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
public interface IRoleService extends IService<RoleEntity> {

    /**
    * 分页查询列表
    * @param pageDTO
    * @return
    */
    PageResult queryRolePage(RolePageDTO pageDTO);

    /**
    * 查询详情
    * @param roleId
    * @return
    */
    RoleDetailVO findRoleDetail(Integer roleId);

    /**
     * 创建
     * @param roleDTO
     *
     */
    void createRole(RoleDTO roleDTO);

    /**
     * 修改
     * @param roleId
     * @param roleDTO
     */
    void updateRole(Integer roleId, RoleDTO roleDTO);

    /**
    * 批量删除
    * @param roleIds
    *
    */
    void deleteRoles(Integer[] roleIds);
}
