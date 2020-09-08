package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull(message = "父菜单不能为空")
    @Min(value = 0, message = "无效的父菜单Id")
    @Schema(name = "parentId", description = "上级资源", type = "Integer")
    private Integer parentId;

    @NotBlank(message = "菜单名不能为空")
    @Size(min = 2, max = 50, message = "菜单名长度必须在2-50个字符之间")
    @Schema(name = "menuName", description = "菜单名", type = "String")
    private String menuName;

    @NotBlank(message = "菜单路径不能为空")
    @Size(min = 2, max = 100, message = "菜单路径长度必须在2-100个字符之间")
    @Schema(name = "menuPath", description = "菜单路径", type = "String")
    private String menuPath;

    @Schema(name = "menuComponent", description = "组件地址", type = "String")
    private String menuComponent;

    @Size(max = 100, message = "菜单重定向路径长度不能超过100个字符")
    @Schema(name = "menuRedirect", description = "重定向路径", type = "String")
    private String menuRedirect;

    @Size(min = 2, max = 100, message = "权限标记长度必须在2-100个字符之间")
    @Schema(name = "menuMark", description = "权限标记", type = "String")
    private String menuMark;

    @NotNull(message = "菜单类型不能为空")
    @Range(min = 1, max = 3, message = "无效得菜单类型值")
    @Schema(name = "menuClass", description = "菜单类型（1目录2菜单3按钮）", type = "Integer")
    private Integer menuClass;

    @NotNull(message = "菜单图标不能为空")
    @Size(min = 1, max = 50, message = "菜单图标长度必须在1-50个字符之间")
    @Schema(name = "menuIcon", description = "菜单图标", type = "String")
    private String menuIcon;

    @Size(max = 150, message = "菜单描述长度不能超过150个字符")
    @Schema(name = "menuDesc", description = "菜单描述", type = "String")
    private String menuDesc;

    @Schema(name = "menuSort", description = "显示排序号", type = "Integer")
    private Integer menuSort;

    @NotNull(message = "是否隐藏不能为空")
    @Range(min = 0, max = 1, message = "无效的是否隐藏值")
    @Schema(name = "isHidden", description = "是否隐藏（1隐藏0显示）", type = "Integer")
    private Integer isHidden;

}
