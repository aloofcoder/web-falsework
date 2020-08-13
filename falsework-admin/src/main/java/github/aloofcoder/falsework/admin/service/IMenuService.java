package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.MenuEntity;
import github.aloofcoder.falsework.admin.pojo.vo.MenuDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.MenuPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.MenuDTO;

/**
 * 系统菜单
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
public interface IMenuService extends IService<MenuEntity> {

    /**
    * 分页查询系统菜单列表
    * @param pageDTO
    * @return
    */
    PageResult queryMenuPage(MenuPageDTO pageDTO);

    /**
    * 查询系统菜单详情
    * @param id
    * @return
    */
    MenuDetailVO findMenuDetail(Integer id);

    /**
     * 创建系统菜单
     * @param menuDTO
     *
     */
    void createMenu(MenuDTO menuDTO);

    /**
     * 修改系统菜单
     * @param id
     * @param menuDTO
     */
    void updateMenu(Integer id, MenuDTO menuDTO);

    /**
    * 批量删除系统菜单
    * @param ids
    *
    */
    void deleteMenus(Integer[] ids);
}
