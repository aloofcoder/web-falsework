package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.admin.pojo.dto.UserDTO;
import github.aloofcoder.falsework.admin.pojo.dto.UserPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.UserEntity;
import github.aloofcoder.falsework.admin.pojo.vo.UserDetailVO;
import github.aloofcoder.falsework.common.util.PageResult;

import java.util.List;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
public interface IUserService extends IService<UserEntity> {

    /**
     * 分页查询列表
     *
     * @param pageDTO
     * @return
     */
    PageResult queryUserPage(UserPageDTO pageDTO);

    /**
     * 查询详情
     *
     * @param userNum
     * @return
     */
    UserDetailVO findUserDetail(String userNum);

    /**
     * 创建
     *
     * @param userDTO
     */
    void createUser(UserDTO userDTO);

    /**
     * 修改用户
     *
     * @param userNum
     * @param userDTO
     */
    void updateUser(String userNum, UserDTO userDTO);

    /**
     * 批量删除
     *
     * @param userNums
     */
    void deleteUsers(String[] userNums);

    /**
     * 通过用户编号查询角色列表
     *
     * @param userNum
     * @return
     */
    List<Integer> findUserRoles(String userNum);

    /**
     * 用户角色分配
     *
     * @param userNum
     * @param roleIds
     */
    void userRoleAssign(String userNum, Integer[] roleIds);
}
