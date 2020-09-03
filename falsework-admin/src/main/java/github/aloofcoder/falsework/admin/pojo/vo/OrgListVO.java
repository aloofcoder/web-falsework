package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author hanle
 * @date 2020/9/3 16:29
 */
@Data
@Schema(name = "OrgListVO", description = "查询部门列表返回参数")
public class OrgListVO {

    @Schema(name = "id", description = "组织Id", type = "Integer")
    private Integer id;

    @Schema(name = "orgName", description = "组织名", type = "String")
    private String orgName;
}
