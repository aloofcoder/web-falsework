package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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

    @Schema(name = "dictName", description = "字典名称", type = "String")
    private String dictName;

    @Schema(name = "dictMark", description = "字典标记", type = "String")
    private String dictMark;
}
