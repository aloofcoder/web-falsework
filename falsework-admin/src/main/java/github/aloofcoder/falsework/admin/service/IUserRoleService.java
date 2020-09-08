package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.admin.pojo.dto.UserRoleDTO;
import github.aloofcoder.falsework.admin.pojo.dto.UserRolePageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.UserRoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.UserRoleDetailVO;
import github.aloofcoder.falsework.common.util.PageResult;

import java.util.List;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
public interface IUserRoleService extends IService<UserRoleEntity> {

    /**
     * 分页查询列表
     *
     * @param pageDTO
     * @return
     */
    PageResult queryUserRolePage(UserRolePageDTO pageDTO);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    UserRoleDetailVO findUserRoleDetail(Integer id);

    /**
     * 创建
     *
     * @param userRoleDTO
     */
    void createUserRole(UserRoleDTO userRoleDTO);

    /**
     * 修改
     *
     * @param id
     * @param userRoleDTO
     */
    void updateUserRole(Integer id, UserRoleDTO userRoleDTO);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteUserRoles(Integer[] ids);

    /**
     * 通过用户编号查询用户角色记录
     *
     * @param userNum
     * @return
     */
    List<UserRoleEntity> findUserRolesByUserNum(String userNum);

    /**
     * 通过用户编号组删除用户与角色关系
     *
     * @param userNums
     * @return
     */
    boolean removeByUserNums(String[] userNums);

    /**
     * 通过用户编号删除用户与角色关系
     *
     * @param userNum
     * @return
     */
    boolean removeByUserNum(String userNum);

    /**
     * 批量添加用户角色
     *
     * @param userNum
     * @param roleIds
     * @return
     */
    boolean saveUserRoles(String userNum, Integer[] roleIds);


    /**
     * 通过用户编号查询角色列表
     *
     * @param userNum
     * @return
     */
    List<String> findUserRoleMarksByUserNum(String userNum);

    /**
     * 通过角色Id列表查询角色
     *
     * @param asList
     * @return
     */
    List<UserRoleEntity> findUserRolesByRoleIds(List<Integer> asList);
}
