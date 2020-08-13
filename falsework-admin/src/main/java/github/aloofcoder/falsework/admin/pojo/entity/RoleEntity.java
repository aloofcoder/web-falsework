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
 * @date 2020-08-14 01:30:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class RoleEntity extends Model<RoleEntity> {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色标记
     */
    private String roleMark;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 排序号
     */
    private Integer roleSort;
    /**
     * 状态(1正常2禁用)
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
        return this.getRoleId();
    }

}
