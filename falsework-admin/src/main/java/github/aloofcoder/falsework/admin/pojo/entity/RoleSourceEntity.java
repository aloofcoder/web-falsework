package github.aloofcoder.falsework.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("sys_role_source")
public class RoleSourceEntity extends Model<RoleSourceEntity> {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 
     */
    private Integer roleId;
    /**
     * 
     */
    private Integer sourceId;

    @Override
    protected Serializable pkVal() {
        return this.getId();
    }

}
