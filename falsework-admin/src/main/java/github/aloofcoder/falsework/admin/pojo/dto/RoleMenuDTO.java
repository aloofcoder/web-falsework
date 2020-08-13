package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Data
@Schema(name = "RoleMenuDTO", description = "添加参数")
public class RoleMenuDTO {


    @Schema(name = "roleId", description = "", type = "Integer")
    private Integer roleId;

    @Schema(name = "menuId", description = "", type = "Integer")
    private Integer menuId;
}
