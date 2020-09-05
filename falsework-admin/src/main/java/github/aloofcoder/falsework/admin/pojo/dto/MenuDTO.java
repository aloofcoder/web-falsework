package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统菜单
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:00
 */
@Data
@Schema(name = "MenuDTO", description = "添加系统菜单参数")
public class MenuDTO {


    @Schema(name = "parentId", description = "上级资源", type = "Integer")
    private Integer parentId;

    @Schema(name = "menuName", description = "菜单名", type = "String")
    private String menuName;

    @Schema(name = "menuPath", description = "菜单路径", type = "String")
    private String menuPath;

    @Schema(name = "menuComponent", description = "组件地址", type = "String")
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

    @Schema(name = "status", description = "状态（1正常2禁用）", type = "Integer")
    private Integer status;

}
