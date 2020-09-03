package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author hanle
 * @date 2020/9/3 17:05
 */
@Data
@Schema(name = "RoleListVO", description = "查询角色列表返回参数")
public class RoleListVO {

    @Schema(name = "roleId", description = "角色Id", type = "Integer")
    private Integer roleId;

    @Schema(name = "roleName", description = "角色名", type = "String")
    private String roleName;
}
