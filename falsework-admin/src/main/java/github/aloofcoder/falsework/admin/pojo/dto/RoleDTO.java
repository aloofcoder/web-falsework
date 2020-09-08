package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:01
 */
@Data
@Schema(name = "RoleDTO", description = "添加参数")
public class RoleDTO {

    @NotEmpty(message = "角色名不能为空")
    @Size(min = 1, max = 10, message = "角色名长度必须在1-10个字符")
    @Schema(name = "roleName", description = "角色名", type = "String")
    private String roleName;

    @NotEmpty(message = "角色标记不能为空")
    @Size(min = 1, max = 10, message = "角色标记长度必须在1-10个字符")
    @Schema(name = "roleMark", description = "角色标记", type = "String")
    private String roleMark;

    @NotEmpty(message = "角色描述不能为空")
    @Size(min = 1, max = 20, message = "角色描述长度必须在1-10个字符")
    @Schema(name = "roleDesc", description = "角色描述", type = "String")
    private String roleDesc;

    @NotNull(message = "排序号不能为空")
    @Schema(name = "roleSort", description = "排序号", type = "Integer")
    private Integer roleSort;

}
