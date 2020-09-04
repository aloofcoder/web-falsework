package github.aloofcoder.falsework.admin.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author hanle
 * @date 2020/9/4 16:25
 */
@Data
@Schema(name = "MenuAuthListVO", description = "查询角色授权目录/菜单列表返回参数")
public class MenuAuthListVO {

    @Schema(name = "id", description = "菜单Id", type = "Integer")
    private Integer id;

    @Schema(name = "parentId", description = "上级菜单", type = "Integer")
    private Integer parentId;

    @Schema(name = "menuName", description = "菜单名", type = "String")
    private String menuName;

    @Schema(name = "menuPath", description = "菜单路径", type = "String")
    private String menuPath;

    private String menuComponent;

    @Schema(name = "menuRedirect", description = "重定向路径", type = "String")
    private String menuRedirect;

    @Schema(name = "menuMark", description = "权限标记", type = "String")
    private String menuMark;

    @Schema(name = "menuClass", description = "菜单类型（1目录2菜单3按钮）", type = "Integer")
    private Integer menuClass;

    @Schema(name = "menuIcon", description = "菜单图标", type = "String")
    private String menuIcon;

    @Schema(name = "menuDesc", description = "菜单描述", type = "String")
    private String menuDesc;

    @Schema(name = "menuSort", description = "显示排序号", type = "Integer")
    private Integer menuSort;

    @Schema(name = "isHidden", description = "是否隐藏（1隐藏0显示）", type = "Integer")
    private Integer isHidden;

    @Schema(name = "children", description = "子菜单", type = "List")
    private List<MenuAuthListVO> children;
}
