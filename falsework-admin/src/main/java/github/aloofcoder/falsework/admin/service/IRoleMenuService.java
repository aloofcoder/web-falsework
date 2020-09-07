package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.admin.pojo.vo.MenuListVO;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.RoleMenuEntity;
import github.aloofcoder.falsework.admin.pojo.vo.RoleMenuDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.RoleMenuPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.RoleMenuDTO;

import java.util.List;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
public interface IRoleMenuService extends IService<RoleMenuEntity> {

    /**
     * 分页查询列表
     *
     * @param pageDTO
     * @return
     */
    PageResult queryRoleMenuPage(RoleMenuPageDTO pageDTO);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    RoleMenuDetailVO findRoleMenuDetail(Integer id);

    /**
     * 创建
     *
     * @param roleMenuDTO
     */
    void createRoleMenu(RoleMenuDTO roleMenuDTO);

    /**
     * 修改
     *
     * @param id
     * @param roleMenuDTO
     */
    void updateRoleMenu(Integer id, RoleMenuDTO roleMenuDTO);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteRoleMenus(Integer[] ids);

    /**
     * 查询角色授权菜单Id列表
     *
     * @return
     */
    List<Integer> findRoleAuthMenuIds();

    /**
     * 移除角色授权的菜单
     *
     * @param roleId
     * @return
     */
    boolean removeByRoleId(Integer roleId);

    /**
     * 保存角色与菜单关系
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean saveRoleMenus(Integer roleId, Integer[] menuIds);
}
