package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author hanle
 * @date 2020/9/3 16:10
 */
@Data
@Schema(name = "UserPageVO", description = "分页查询用户返回参数")
public class UserPageVO {

    @Schema(name = "userNum", description = "系统编号", type = "String")
    private String userNum;

    @Schema(name = "userName", description = "姓名", type = "String")
    private String userName;

    @Schema(name = "loginName", description = "登录名", type = "String")
    private String loginName;

    @Schema(name = "phoneNum", description = "手机号", type = "String")
    private String phoneNum;

    @Schema(name = "userEmail", description = "邮箱", type = "String")
    private String userEmail;

    @Schema(name = "birthDate", description = "生日", type = "Date")
    private Date birthDate;

    @Schema(name = "status", description = "状态（1正常）", type = "Integer")
    private Integer status;
}
