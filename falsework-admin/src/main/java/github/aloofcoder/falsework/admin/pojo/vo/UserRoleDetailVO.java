package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Data
@Schema(name = "UserRoleDetailVO", description = "查询详情返回参数")
public class UserRoleDetailVO {


    @Schema(name = "roleId", description = "", type = "Integer")
    private Integer roleId;

    @Schema(name = "userNum", description = "", type = "String")
    private String userNum;
}
