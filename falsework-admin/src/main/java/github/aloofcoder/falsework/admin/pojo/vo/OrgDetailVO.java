package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

/**
 * 公司组织
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:00
 */
@Data
@Schema(name = "OrgDetailVO", description = "查询公司组织详情返回参数")
public class OrgDetailVO {

    @Schema(name = "id", description = "组织Id", type = "Integer")
    private Integer id;

    @Schema(name = "parentId", description = "父组织", type = "Integer")
    private Integer parentId;

    @Schema(name = "orgName", description = "组织名", type = "String")
    private String orgName;

    @Schema(name = "orgDesc", description = "组织描述", type = "String")
    private String orgDesc;

    @Schema(name = "orgSort", description = "组织排序", type = "Integer")
    private Integer orgSort;

}
