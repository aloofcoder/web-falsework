package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.OrgUserEntity;
import github.aloofcoder.falsework.admin.pojo.vo.OrgUserDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserDTO;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
public interface IOrgUserService extends IService<OrgUserEntity> {

    /**
    * 分页查询列表
    * @param pageDTO
    * @return
    */
    PageResult queryOrgUserPage(OrgUserPageDTO pageDTO);

    /**
    * 查询详情
    * @param id
    * @return
    */
    OrgUserDetailVO findOrgUserDetail(Integer id);

    /**
     * 创建
     * @param orgUserDTO
     *
     */
    void createOrgUser(OrgUserDTO orgUserDTO);

    /**
     * 修改
     * @param id
     * @param orgUserDTO
     */
    void updateOrgUser(Integer id, OrgUserDTO orgUserDTO);

    /**
    * 批量删除
    * @param ids
    *
    */
    void deleteOrgUsers(Integer[] ids);
}
