package github.aloofcoder.falsework.common.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-11 23:54:40
 */
@Data
@ParameterObject
public class PageDTO {

    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页数必须大于等于1")
    @Schema(name = "page", description = "页数", type = "Integer", required = true)
    private Integer page;

    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数必须大于等于1")
    @Max(value = 500, message = "每页条数不能超过500")
    @Schema(name = "limit", description = "每页条数", type = "Integer", required = true)
    private Integer limit;

    @Schema(name = "field", description = "排序字段", type = "String")
    private String field;

    @Schema(name = "desc", description = "排序类型", type = "Integer")
    private Integer desc;
}
