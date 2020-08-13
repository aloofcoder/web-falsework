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
@TableName("sys_source")
public class SourceEntity extends Model<SourceEntity> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 资源名称
     */
    private String sourceName;
    /**
     * 所属模块
     */
    private String sourceModule;
    /**
     * 资源请求方法
     */
    private String method;
    /**
     * 资源url
     */
    private String sourceUrl;
    /**
     * 系统生成的资源唯一号
     */
    private String sourceCode;
    /**
     * (1正常2禁用)
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
    private Date editTme;

    @Override
    protected Serializable pkVal() {
        return this.getId();
    }

}
