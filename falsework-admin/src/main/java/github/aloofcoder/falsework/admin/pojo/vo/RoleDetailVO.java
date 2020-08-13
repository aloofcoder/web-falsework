package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Data
@Schema(name = "RoleDetailVO", description = "查询详情返回参数")
public class RoleDetailVO {


    @Schema(name = "roleName", description = "角色名", type = "String")
    private String roleName;

    @Schema(name = "roleMark", description = "角色标记", type = "String")
    private String roleMark;

    @Schema(name = "roleDesc", description = "角色描述", type = "String")
    private String roleDesc;

    @Schema(name = "roleSort", description = "排序号", type = "Integer")
    private Integer roleSort;

    @Schema(name = "status", description = "状态(1正常2禁用)", type = "Integer")
    private Integer status;

    @Schema(name = "createBy", description = "", type = "String")
    private String createBy;

    @Schema(name = "editBy", description = "", type = "String")
    private String editBy;

    @Schema(name = "createTime", description = "", type = "Date")
    private Date createTime;

    @Schema(name = "editTime", description = "", type = "Date")
    private Date editTime;
}
