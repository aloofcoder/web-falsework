package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@Data
@Schema(name = "DictItemDetailVO", description = "查询详情返回参数")
public class DictItemDetailVO {


    @Schema(name = "dictId", description = "", type = "Integer")
    private Integer dictId;

    @Schema(name = "label", description = "", type = "String")
    private String label;

    @Schema(name = "value", description = "", type = "String")
    private String value;

    @Schema(name = "sort", description = "", type = "Integer")
    private Integer sort;
}
