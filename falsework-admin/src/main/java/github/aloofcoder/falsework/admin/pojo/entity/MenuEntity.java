package github.aloofcoder.falsework.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 14:48:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class MenuEntity extends Model<MenuEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 上级资源
     */
    private Integer parentId;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 菜单路径
     */
    private String menuPath;

    /**
     * 组件地址
     */
    private String menuComponent;
    /**
     * 重定向路径
     */
    private String menuRedirect;
    /**
     * 权限标记
     */
    private String menuMark;
    /**
     * 菜单类型（1目录2菜单3按钮）
     */
    private Integer menuClass;

    /**
     * 菜单图标
     */
    private String menuIcon;
    /**
     * 菜单描述
     */
    private String menuDesc;
    /**
     * 显示排序号
     */
    private Integer menuSort;
    /**
     * 是否隐藏（1隐藏0显示）
     */
    private Integer isHidden;
    /**
     * 状态（1正常2禁用）
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

    @TableField(exist = false)
    private List<MenuEntity> children;

    @Override
    protected Serializable pkVal() {
        return this.getId();
    }

}
