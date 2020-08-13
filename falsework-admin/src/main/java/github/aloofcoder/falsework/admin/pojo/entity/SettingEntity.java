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
 * 系统全局设置表
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_setting")
public class SettingEntity extends Model<SettingEntity> {

    private static final long serialVersionUID=1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Override
    protected Serializable pkVal() {
        return this.getId();
    }

}
