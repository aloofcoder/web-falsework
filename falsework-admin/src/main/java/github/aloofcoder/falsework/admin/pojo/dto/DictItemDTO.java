package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:01
 */
@Data
@Schema(name = "DictItemDTO", description = "添加参数")
public class DictItemDTO {

    @Schema(name = "dictId", description = "", type = "Integer")
    private Integer dictId;

    @Schema(name = "label", description = "", type = "String")
    private String label;

    @Schema(name = "value", description = "", type = "String")
    private String value;

    @Schema(name = "sort", description = "", type = "Integer")
    private Integer sort;
}
