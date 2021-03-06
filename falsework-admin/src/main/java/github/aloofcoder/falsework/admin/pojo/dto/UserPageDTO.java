package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;
import github.aloofcoder.falsework.common.pojo.dto.PageDTO;

/**
 *
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:01
 */
@Data
@ParameterObject
public class UserPageDTO extends PageDTO {

    @Schema(name = "condition", description = "用户名/登录名/邮箱", type = "String")
    private String condition;

}
