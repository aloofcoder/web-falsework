package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull(message = "夫组织不能为空")
    @Min(value = 0, message = "无效得父组织")
    @Schema(name = "parentId", description = "父组织", type = "Integer")
    private Integer parentId;

    @NotBlank(message = "组织名不能为空")
    @Size(min = 2, max = 10, message = "组织名长度必须在2-10个字符之间")
    @Schema(name = "orgName", description = "组织名", type = "String")
    private String orgName;

    @NotBlank(message = "组织描述不能为空")
    @Size(min = 2, max = 20, message = "组织描述长度必须在2-20个字符之间")
    @Schema(name = "orgDesc", description = "组织描述", type = "String")
    private String orgDesc;

    @NotNull(message = "排序号不能为空")
    @Range(min = 0, message = "显示排序号必须大于等于0")
    @Schema(name = "orgSort", description = "组织排序", type = "Integer")
    private Integer orgSort;

}
