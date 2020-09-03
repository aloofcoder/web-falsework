package github.aloofcoder.falsework.admin.pojo.dto;

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
@Schema(name = "OrgDTO", description = "添加公司组织参数")
public class OrgDTO {


    @Schema(name = "parentId", description = "父组织", type = "Integer")
    private Integer parentId;

    @Schema(name = "orgName", description = "组织名", type = "String")
    private String orgName;

    @Schema(name = "orgDesc", description = "组织描述", type = "String")
    private String orgDesc;

    @Schema(name = "orgSort", description = "组织排序", type = "Integer")
    private Integer orgSort;

    @Schema(name = "status", description = "1(正常）", type = "Integer")
    private Integer status;




}
