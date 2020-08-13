package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.OrgUserDao;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserDTO;
import github.aloofcoder.falsework.admin.pojo.entity.OrgUserEntity;
import github.aloofcoder.falsework.admin.service.IOrgUserService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.vo.OrgUserDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * 
 *
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
}
