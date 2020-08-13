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
 * 公司组织
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_org")
public class OrgEntity extends Model<OrgEntity> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父组织
     */
    private Integer parentId;
    /**
     * 组织名
     */
    private String orgName;
    /**
     * 组织描述
     */
    private String orgDesc;
    /**
     * 组织排序
     */
    private Integer orgSort;
    /**
     * 1(正常）
     */
    private Integer status;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String editBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date editTime;

    @Override
    protected Serializable pkVal() {
        return this.getId();
    }

}
