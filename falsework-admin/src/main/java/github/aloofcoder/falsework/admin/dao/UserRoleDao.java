package github.aloofcoder.falsework.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.aloofcoder.falsework.admin.pojo.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Repository
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {

    /**
     * 查询角色名通过用户
     *
     * @param userNums
     * @return
     */
    List<Map<String, String>> findUserRoleNamesByUserNums(@Param("userNums") List<String> userNums);
}
