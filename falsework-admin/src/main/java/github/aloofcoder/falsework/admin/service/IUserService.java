package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.UserEntity;
import github.aloofcoder.falsework.admin.pojo.vo.UserDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.UserPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.UserDTO;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
public interface IUserService extends IService<UserEntity> {

    /**
    * 分页查询列表
    * @param pageDTO
    * @return
    */
    PageResult queryUserPage(UserPageDTO pageDTO);

    /**
    * 查询详情
    * @param id
    * @return
    */
    UserDetailVO findUserDetail(Integer id);

    /**
     * 创建
     * @param userDTO
     *
     */
    void createUser(UserDTO userDTO);

    /**
     * 修改
     * @param id
     * @param userDTO
     */
    void updateUser(Integer id, UserDTO userDTO);

    /**
    * 批量删除
    * @param ids
    *
    */
    void deleteUsers(Integer[] ids);
}
