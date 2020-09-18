package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author hanle
 * @date 2020/9/4 16:25
 */
@Data
@Schema(name = "MenuListVO", description = "查询目录/菜单列表返回参数")
public class MenuListVO {

    @Schema(name = "id", description = "菜单Id", type = "Integer")
    private Integer id;

    @Schema(name = "parentId", description = "上级菜单", type = "Integer")
    private Integer parentId;

    @Schema(name = "menuName", description = "菜单名", type = "String")
    private String menuName;

    @Schema(name = "menuSort", description = "菜单排序号", type = "Integer")
    private Integer menuSort;

    @Schema(name = "children", description = "子菜单", type = "List")
    private List<MenuListVO> children;
}
