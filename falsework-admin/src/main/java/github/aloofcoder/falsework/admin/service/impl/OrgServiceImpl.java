package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.OrgDao;
import github.aloofcoder.falsework.admin.pojo.dto.OrgDTO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.OrgEntity;
import github.aloofcoder.falsework.admin.pojo.vo.OrgDetailVO;
import github.aloofcoder.falsework.admin.pojo.vo.OrgListVO;
import github.aloofcoder.falsework.admin.pojo.vo.OrgTreeVO;
import github.aloofcoder.falsework.admin.service.IOrgService;
import github.aloofcoder.falsework.common.util.PageResult;
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

    @Override
    public PageResult queryOrgPage(OrgPageDTO pageDTO) {
        IPage<OrgEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<OrgEntity>());
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
        OrgEntity entity = new OrgEntity();
        BeanUtils.copyProperties(orgDTO, entity);
        entity.setCreateBy("1");
        entity.setEditBy("1");
        this.save(entity);
    }

    @Override
    public void updateOrg(Integer id, OrgDTO orgDTO) {
        OrgEntity entity = this.getOne(new QueryWrapper<OrgEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(orgDTO, entity);
        update(entity, new UpdateWrapper<OrgEntity>().eq("id", id));
    }

    @Override
    public void deleteOrgs(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
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
}
