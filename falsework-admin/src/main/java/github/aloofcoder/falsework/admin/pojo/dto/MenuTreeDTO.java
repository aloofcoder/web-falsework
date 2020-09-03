package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

/**
 * @author hanle
 * @date 2020/8/20 15:57
 */
@Data
@ParameterObject
public class MenuTreeDTO {

    @Schema(name = "condition", description = "菜单名称/菜单路径", type = "string")
    private String condition;
}
