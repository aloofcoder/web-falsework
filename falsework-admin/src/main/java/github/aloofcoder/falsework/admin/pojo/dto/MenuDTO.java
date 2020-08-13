package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

/**
 * 系统菜单
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
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

    @Schema(name = "menuRedirect", description = "重定向路径", type = "String")
    private String menuRedirect;

    @Schema(name = "menuMark", description = "菜单标记", type = "String")
    private String menuMark;

    @Schema(name = "menuMeta", description = "菜单元信息(json格式数据)", type = "String")
    private String menuMeta;

    @Schema(name = "menuDesc", description = "菜单描述", type = "String")
    private String menuDesc;

    @Schema(name = "menuSort", description = "显示排序号", type = "Integer")
    private Integer menuSort;

    @Schema(name = "isHidden", description = "是否隐藏（1隐藏0显示）", type = "Integer")
    private Integer isHidden;

    @Schema(name = "status", description = "状态（1正常2禁用）", type = "Integer")
    private Integer status;

    @Schema(name = "createBy", description = "创建人", type = "String")
    private String createBy;

    @Schema(name = "editBy", description = "修改人", type = "String")
    private String editBy;

    @Schema(name = "createTime", description = "创建时间", type = "Date")
    private Date createTime;

    @Schema(name = "editTime", description = "修改时间", type = "Date")
    private Date editTime;
}
