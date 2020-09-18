package github.aloofcoder.falsework.admin.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.aloofcoder.falsework.admin.validate.ValidEmail;
import github.aloofcoder.falsework.admin.validate.ValidPhoneNum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:01
 */
@Data
@Schema(name = "UserDTO", description = "添加参数")
public class UserDTO {

    @NotBlank(message = "用户姓名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度必须在2-50个字符")
    @Schema(name = "userName", description = "姓名", type = "String")
    private String userName;

    @NotBlank(message = "用户登录名不能为空")
    @Size(min = 4, max = 20, message = "登录名长度必须在2-50个字符")
    @Schema(name = "loginName", description = "登录名", type = "String")
    private String loginName;

    @Size(min = 6, max = 20, message = "登录密码长度必须在6-20个字符")
    @Schema(name = "loginPwd", description = "登录密码", type = "String")
    private String loginPwd;

    @NotBlank(message = "手机号不能为空")
    @ValidPhoneNum(message = "无效的手机号")
    @Schema(name = "phoneNum", description = "手机号", type = "String")
    private String phoneNum;

    @Size(min = 1, max = 50, message = "邮箱地址太长")
    @ValidEmail(message = "无效的邮箱地址")
    @Schema(name = "userEmail", description = "邮箱", type = "String")
    private String userEmail;

    @NotNull(message = "生日不能为空")
    @Past(message = "生日不能大于今日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8", locale = "zn")
    @Schema(name = "birthDate", description = "生日", type = "Date")
    private Date birthDate;

    @NotNull(message = "部门不能为空")
    @Min(value = 1, message = "无效的部门")
    @Schema(name = "orgId", description = "部门Id", type = "Integer")
    private Integer orgId;

}
