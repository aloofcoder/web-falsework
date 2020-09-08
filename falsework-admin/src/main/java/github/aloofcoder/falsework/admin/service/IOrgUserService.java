package github.aloofcoder.falsework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserDTO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.OrgUserEntity;
import github.aloofcoder.falsework.admin.pojo.vo.OrgUserDetailVO;
import github.aloofcoder.falsework.common.util.PageResult;

import java.util.List;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
public interface IOrgUserService extends IService<OrgUserEntity> {

    /**
     * 分页查询列表
     *
     * @param pageDTO
     * @return
     */
    PageResult queryOrgUserPage(OrgUserPageDTO pageDTO);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    OrgUserDetailVO findOrgUserDetail(Integer id);

    /**
     * 创建
     *
     * @param orgUserDTO
     */
    void createOrgUser(OrgUserDTO orgUserDTO);

    /**
     * 修改
     *
     * @param id
     * @param orgUserDTO
     */
    void updateOrgUser(Integer id, OrgUserDTO orgUserDTO);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteOrgUsers(Integer[] ids);

    /**
     * 通过用户Id查询组织用户
     *
     * @param userNum
     * @return
     */
    OrgUserEntity findOrgUserByUserNum(String userNum);

    /**
     * 添加用户及组织关系
     *
     * @param userNum
     * @param orgId
     * @return
     */
    boolean saveOrgUser(String userNum, Integer orgId);

    /**
     * 通过用户编号移除用户与组织关系
     *
     * @param userNum
     * @return
     */
    boolean removeByUserNum(String userNum);

    /**
     * 通过用户编号组删除用户与组织关系
     *
     * @param userNums
     * @return
     */
    boolean removeByUserNums(String[] userNums);

    /**
     * 通过组织Id列表查询组织下的用户
     *
     * @param orgIds
     * @return
     */
    List<OrgUserEntity> findOrgUserByRoleIds(List<Integer> orgIds);

}
