package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.config.BaseContextUtil;
import github.aloofcoder.falsework.admin.dao.OrgDao;
import github.aloofcoder.falsework.admin.pojo.dto.OrgDTO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.OrgEntity;
import github.aloofcoder.falsework.admin.pojo.entity.OrgUserEntity;
import github.aloofcoder.falsework.admin.pojo.vo.OrgDetailVO;
import github.aloofcoder.falsework.admin.pojo.vo.OrgListVO;
import github.aloofcoder.falsework.admin.pojo.vo.OrgTreeVO;
import github.aloofcoder.falsework.admin.service.IOrgService;
import github.aloofcoder.falsework.admin.service.IOrgUserService;
import github.aloofcoder.falsework.common.util.AppException;
import github.aloofcoder.falsework.common.util.ErrorCode;
import github.aloofcoder.falsework.common.util.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 公司组织
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgDao, OrgEntity> implements IOrgService {

    @Autowired
    private OrgDao orgDao;
    @Autowired
    private IOrgUserService orgUserService;

    @Override
    public PageResult queryOrgPage(OrgPageDTO pageDTO) {
        QueryWrapper<OrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("org_sort");
        if (StringUtils.isNotBlank(pageDTO.getCondition())) {
            queryWrapper.like("org_name", pageDTO.getCondition());
        }
        IPage<OrgEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                queryWrapper);
        return new PageResult(page);
    }

    @Override
    public OrgDetailVO findOrgDetail(Integer id) {
        OrgEntity entity = this.getOne(new QueryWrapper<OrgEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        OrgDetailVO vo = new OrgDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createOrg(OrgDTO orgDTO) {
        // 验证组织名称是否重复
        OrgEntity orgNameEntity = getByOrgName(orgDTO.getOrgName());
        if (Objects.nonNull(orgNameEntity)) {
            throw new AppException(ErrorCode.ORG_NAME_REPEAT);
        }
        String loginNum = BaseContextUtil.getLoginNum();
        OrgEntity entity = new OrgEntity();
        BeanUtils.copyProperties(orgDTO, entity);
        entity.setCreateBy(loginNum);
        entity.setEditBy(loginNum);
        boolean saveOrgFlag = this.save(entity);
        if (!saveOrgFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
    }

    @Override
    public void updateOrg(Integer id, OrgDTO orgDTO) {
        // 验证组织名称是否重复
        OrgEntity orgNameEntity = getByOrgName(orgDTO.getOrgName());
        if (Objects.nonNull(orgNameEntity) && !id.equals(orgNameEntity.getId())) {
            throw new AppException(ErrorCode.ORG_NAME_REPEAT);
        }
        String loginNum = BaseContextUtil.getLoginNum();
        OrgEntity entity = this.getOne(new QueryWrapper<OrgEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new AppException(ErrorCode.ORG_ID_INVALID);
        }
        BeanUtils.copyProperties(orgDTO, entity);
        entity.setEditBy(loginNum);
        boolean updateOrgFlag = update(entity, new UpdateWrapper<OrgEntity>().eq("id", id));
        if (!updateOrgFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
    }

    @Override
    public void deleteOrgs(Integer[] ids) {
        List<OrgUserEntity> orgUserList = orgUserService.findOrgUserByRoleIds(Arrays.asList(ids));
        if (orgUserList.size() > 0) {
            List<Integer> orgIds = orgUserList.stream().map(OrgUserEntity::getOrgId).collect(Collectors.toList());
            String orgNames = this.list(new QueryWrapper<OrgEntity>().in("id", orgIds))
                    .stream().map(OrgEntity::getOrgName).collect(Collectors.joining("，"));
            throw new AppException(ErrorCode.ORG_USED.getCode(), String.format("删除组织【%1$s】失败，", orgNames) + ErrorCode.ORG_USED.getMsg());
        }
        boolean removeOrgFlag = this.removeByIds(Arrays.asList(ids));
        if (!removeOrgFlag) {
            throw new AppException(ErrorCode.DB_REQ_ERR);
        }
    }

    @Override
    public List<OrgListVO> findOrgList() {
        List<OrgEntity> list = this.list();
        List<OrgListVO> orgList = new ArrayList<>();
        list.stream().forEach(item -> {
            OrgListVO vo = new OrgListVO();
            BeanUtils.copyProperties(item, vo);
            orgList.add(vo);
        });
        return orgList;
    }

    @Override
    public OrgEntity findOrgByOrgId(Integer orgId) {
        OrgEntity entity = this.getOne(new QueryWrapper<OrgEntity>().eq("id", orgId));
        return entity;
    }

    @Override
    public List<OrgTreeVO> findOrgTree() {
        List<OrgTreeVO> list = this.list().stream().map(item -> {
            OrgTreeVO vo = new OrgTreeVO();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        List<OrgTreeVO> trees = new ArrayList<>();
        list.forEach(item -> {
            if (item.getParentId() == -1) {
                trees.add(item);
            }
            list.forEach(org -> {
                if (item.getId().equals(org.getParentId())) {
                    if (Objects.isNull(item.getChildren())) {
                        item.setChildren(new ArrayList<>());
                    }
                    item.getChildren().add(org);
                }
            });
        });
        return trees;
    }

    /**
     * 通过组织名获取组织
     *
     * @param orgName
     * @return
     */
    private OrgEntity getByOrgName(String orgName) {
        QueryWrapper<OrgEntity> orgNameWrapper = new QueryWrapper<>();
        orgNameWrapper.eq("org_name", orgName);
        OrgEntity entity = getOne(orgNameWrapper);
        if (Objects.nonNull(entity)) {
            return entity;
        }
        return null;
    }
}
