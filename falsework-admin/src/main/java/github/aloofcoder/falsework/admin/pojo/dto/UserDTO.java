package github.aloofcoder.falsework.admin.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@Data
@Schema(name = "UserDTO", description = "添加参数")
public class UserDTO {


    @Schema(name = "userNum", description = "系统编号", type = "String")
    private String userNum;

    @Schema(name = "userName", description = "姓名", type = "String")
    private String userName;

    @Schema(name = "loginName", description = "登录名", type = "String")
    private String loginName;

    @Schema(name = "loginPwd", description = "登录密码", type = "String")
    private String loginPwd;

    @Schema(name = "phoneNum", description = "手机号", type = "String")
    private String phoneNum;

    @Schema(name = "userEmail", description = "邮箱", type = "String")
    private String userEmail;

    @Schema(name = "birthDate", description = "生日", type = "Date")
    private Date birthDate;

    @Schema(name = "status", description = "状态（1正常）", type = "Integer")
    private Integer status;

    @Schema(name = "createBy", description = "", type = "String")
    private String createBy;

    @Schema(name = "editBy", description = "", type = "String")
    private String editBy;

    @Schema(name = "createTime", description = "", type = "Date")
    private Date createTime;

    @Schema(name = "editTime", description = "", type = "Date")
    private Date editTime;
}
