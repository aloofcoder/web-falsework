package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:01
 */
@Data
@Schema(name = "DictItemDTO", description = "添加参数")
public class DictItemDTO {

    @NotNull(message = "字典名称不能为空")
    @Min(value = 1, message = "无效的字典名称值")
    @Schema(name = "dictId", description = "字典名称", type = "Integer")
    private Integer dictId;

    @NotEmpty(message = "字典标签不能为空")
    @Size(min = 2, max = 20, message = "字典标签长度必须在2-20个字符之间")
    @Schema(name = "label", description = "字典标签", type = "String")
    private String label;

    @NotEmpty(message = "字典值不能为空")
    @Size(min = 2, max = 20, message = "字典值长度必须在2-20个字符之间")
    @Schema(name = "value", description = "字典值", type = "String")
    private String value;

    @NotNull(message = "显示排序号不能为空")
    @Range(min = 1, max = 99999, message = "显示排序号必须在1~99999之间")
    @Schema(name = "sort", description = "显示排序号", type = "Integer")
    private Integer sort;
}
