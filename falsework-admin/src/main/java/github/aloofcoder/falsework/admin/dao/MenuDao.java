package github.aloofcoder.falsework.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import github.aloofcoder.falsework.admin.pojo.entity.MenuEntity;

/**
 * 系统菜单
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 14:48:53
 */
@Repository
public interface MenuDao extends BaseMapper<MenuEntity> {

}
