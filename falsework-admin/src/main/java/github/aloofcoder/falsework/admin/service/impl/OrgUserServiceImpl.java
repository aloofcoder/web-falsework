package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.OrgUserDao;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserDTO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.OrgUserEntity;
import github.aloofcoder.falsework.admin.pojo.vo.OrgUserDetailVO;
import github.aloofcoder.falsework.admin.service.IOrgUserService;
import github.aloofcoder.falsework.common.util.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Service
public class OrgUserServiceImpl extends ServiceImpl<OrgUserDao, OrgUserEntity> implements IOrgUserService {

    @Autowired
    private OrgUserDao orgUserDao;

    @Override
    public PageResult queryOrgUserPage(OrgUserPageDTO pageDTO) {
        IPage<OrgUserEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                new QueryWrapper<OrgUserEntity>());
        return new PageResult(page);
    }

    @Override
    public OrgUserDetailVO findOrgUserDetail(Integer id) {
        OrgUserEntity entity = this.getOne(new QueryWrapper<OrgUserEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        OrgUserDetailVO vo = new OrgUserDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createOrgUser(OrgUserDTO orgUserDTO) {
        OrgUserEntity entity = new OrgUserEntity();
        BeanUtils.copyProperties(orgUserDTO, entity);
        this.save(entity);
    }

    @Override
    public void updateOrgUser(Integer id, OrgUserDTO orgUserDTO) {
        OrgUserEntity entity = this.getOne(new QueryWrapper<OrgUserEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(orgUserDTO, entity);
        update(entity, new UpdateWrapper<OrgUserEntity>().eq("id", id));
    }

    @Override
    public void deleteOrgUsers(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public OrgUserEntity findOrgUserByUserNum(String userNum) {
        OrgUserEntity entity = this.getOne(new QueryWrapper<OrgUserEntity>().eq("user_num", userNum));
        if (Objects.isNull(entity)) {
            return null;
        }
        return entity;
    }

    @Override
    public boolean saveOrgUser(String userNum, Integer orgId) {
        OrgUserEntity entity = new OrgUserEntity();
        entity.setOrgId(orgId);
        entity.setUserNum(userNum);
        return this.save(entity);
    }

    @Override
    public boolean removeByUserNum(String userNum) {
        return this.remove(new QueryWrapper<OrgUserEntity>().eq("user_num", userNum));
    }

    @Override
    public boolean removeByUserNums(String[] userNums) {
        QueryWrapper<OrgUserEntity> queryWrapper = new QueryWrapper<OrgUserEntity>().in("user_num", userNums);
        List<OrgUserEntity> list = this.list(queryWrapper);
        if (list.size() <= 0) {
            return true;
        }
        return this.remove(queryWrapper);
    }

    @Override
    public List<OrgUserEntity> findOrgUserByRoleIds(List<Integer> orgIds) {
        if (orgIds.size() == 0) {
            return new ArrayList<>();
        }
        List<OrgUserEntity> list = this.list(new QueryWrapper<OrgUserEntity>().in("org_id", orgIds));
        return list;
    }
}
