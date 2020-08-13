package github.aloofcoder.falsework.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class UserEntity extends Model<UserEntity> {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 系统编号
     */
    private String userNum;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String loginPwd;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 生日
     */
    private Date birthDate;
    /**
     * 状态（1正常）
     */
    private Integer status;
    /**
     * 
     */
    private String createBy;
    /**
     * 
     */
    private String editBy;
    /**
     * 
     */
    private Date createTime;
    /**
     * 
     */
    private Date editTime;

    @Override
    protected Serializable pkVal() {
        return this.getId();
    }

}
