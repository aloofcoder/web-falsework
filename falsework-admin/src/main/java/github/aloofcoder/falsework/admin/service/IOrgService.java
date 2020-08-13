package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.entity.OrgEntity;
import github.aloofcoder.falsework.admin.pojo.vo.OrgDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgDTO;

/**
 * 公司组织
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
public interface IOrgService extends IService<OrgEntity> {

    /**
    * 分页查询公司组织列表
    * @param pageDTO
    * @return
    */
    PageResult queryOrgPage(OrgPageDTO pageDTO);

    /**
    * 查询公司组织详情
    * @param id
    * @return
    */
    OrgDetailVO findOrgDetail(Integer id);

    /**
     * 创建公司组织
     * @param orgDTO
     *
     */
    void createOrg(OrgDTO orgDTO);

    /**
     * 修改公司组织
     * @param id
     * @param orgDTO
     */
    void updateOrg(Integer id, OrgDTO orgDTO);

    /**
    * 批量删除公司组织
    * @param ids
    *
    */
    void deleteOrgs(Integer[] ids);
}
