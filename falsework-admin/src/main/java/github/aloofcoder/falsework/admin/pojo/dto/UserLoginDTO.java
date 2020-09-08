package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户登录接口参数
 *
 * @author hanle
 * @date 2020-09-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UserLoginDTO", description = "用户登录接口参数")
public class UserLoginDTO implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    @NotEmpty(message = "用户名不能为空")
    @Schema(name = "username", description = "用户名", type = "String", required = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Schema(name = "password", description = "密码", type = "String", required = true)
    private String password;

}

