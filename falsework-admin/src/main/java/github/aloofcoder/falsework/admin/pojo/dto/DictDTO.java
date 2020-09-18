package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 数据字典
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:00
 */
@Data
@Schema(name = "DictDTO", description = "添加数据字典参数")
public class DictDTO {

    @NotEmpty(message = "字典名称不能为空")
    @Size(min = 2, max = 20, message = "字典名称长度必须在2-20个字符之间")
    @Schema(name = "dictName", description = "字典名称", type = "String")
    private String dictName;

    @NotEmpty(message = "字典标记不能为空")
    @Size(min = 4, max = 30, message = "字典标记长度必须在4-30个字符之间")
    @Schema(name = "dictMark", description = "字典标记", type = "String")
    private String dictMark;
}
