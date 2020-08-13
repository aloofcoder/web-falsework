package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.UserRoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.UserRoleDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.UserRolePageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.UserRoleDTO;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
public interface IUserRoleService extends IService<UserRoleEntity> {

    /**
    * 分页查询列表
    * @param pageDTO
    * @return
    */
    PageResult queryUserRolePage(UserRolePageDTO pageDTO);

    /**
    * 查询详情
    * @param id
    * @return
    */
    UserRoleDetailVO findUserRoleDetail(Integer id);

    /**
     * 创建
     * @param userRoleDTO
     *
     */
    void createUserRole(UserRoleDTO userRoleDTO);

    /**
     * 修改
     * @param id
     * @param userRoleDTO
     */
    void updateUserRole(Integer id, UserRoleDTO userRoleDTO);

    /**
    * 批量删除
    * @param ids
    *
    */
    void deleteUserRoles(Integer[] ids);
}
