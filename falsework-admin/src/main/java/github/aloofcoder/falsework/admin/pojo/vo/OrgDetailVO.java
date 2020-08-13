package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

/**
 * 公司组织
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Data
@Schema(name = "OrgDetailVO", description = "查询公司组织详情返回参数")
public class OrgDetailVO {


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

    @Schema(name = "createBy", description = "创建人", type = "String")
    private String createBy;

    @Schema(name = "editBy", description = "修改人", type = "String")
    private String editBy;

    @Schema(name = "createTime", description = "创建时间", type = "Date")
    private Date createTime;

    @Schema(name = "editTime", description = "修改时间", type = "Date")
    private Date editTime;
}
