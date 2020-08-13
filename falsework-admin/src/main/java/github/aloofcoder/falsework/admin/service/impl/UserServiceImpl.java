package github.aloofcoder.falsework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import github.aloofcoder.falsework.admin.dao.UserDao;
import github.aloofcoder.falsework.admin.pojo.dto.UserPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.UserDTO;
import github.aloofcoder.falsework.admin.pojo.entity.UserEntity;
import github.aloofcoder.falsework.admin.service.IUserService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.admin.pojo.vo.UserDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @date 2020-08-14 01:30:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageResult queryUserPage(UserPageDTO pageDTO) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(pageDTO.getField()) && pageDTO.getDesc() == 1) {
            wrapper.orderByDesc(pageDTO.getField());
        } else if (StringUtils.isNotBlank(pageDTO.getField()) && pageDTO.getDesc() == 0){
            wrapper.orderByAsc(pageDTO.getField());
        }
        IPage<UserEntity> page = this.page(
                new Page<>(pageDTO.getPage(), pageDTO.getLimit()),
                wrapper);
        return new PageResult(page);
    }

    @Override
    public UserDetailVO findUserDetail(Integer id) {
        UserEntity entity = this.getOne(new QueryWrapper<UserEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            return null;
        }
        UserDetailVO vo = new UserDetailVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


    @Override
    public void createUser(UserDTO userDTO) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userDTO, entity);
        this.save(entity);
    }

    @Override
    public void updateUser(Integer id, UserDTO userDTO) {
        UserEntity entity = this.getOne(new QueryWrapper<UserEntity>().eq("id", id));
        if (Objects.isNull(entity)) {
            throw new IllegalArgumentException();
        }
        BeanUtils.copyProperties(userDTO, entity);
        update(entity, new UpdateWrapper<UserEntity>().eq("id", id));
    }

    @Override
    public void deleteUsers(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
