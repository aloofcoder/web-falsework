package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

/**
 *
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:01
 */
@Data
@Schema(name = "RoleDTO", description = "添加参数")
public class RoleDTO {

    @Schema(name = "roleName", description = "角色名", type = "String")
    private String roleName;

    @Schema(name = "roleMark", description = "角色标记", type = "String")
    private String roleMark;

    @Schema(name = "roleDesc", description = "角色描述", type = "String")
    private String roleDesc;

    @Schema(name = "roleSort", description = "排序号", type = "Integer")
    private Integer roleSort;

}
