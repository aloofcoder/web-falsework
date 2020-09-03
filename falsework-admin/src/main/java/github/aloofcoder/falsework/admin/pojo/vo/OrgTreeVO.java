package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author hanle
 * @date 2020-09-04
 */
@Data
@Schema(name = "OrgTreeVO", description = "查询公司组织树返回参数")
public class OrgTreeVO {

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

    @Schema(name = "status", description = "1(正常）", type = "Integer")
    private Integer status;

    @Schema(name = "children", description = "子组织", type = "List")
    private List<OrgTreeVO> children;
}
