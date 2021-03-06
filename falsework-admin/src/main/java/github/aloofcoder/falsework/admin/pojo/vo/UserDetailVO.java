package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:01
 */
@Data
@Schema(name = "UserDetailVO", description = "查询详情返回参数")
public class UserDetailVO {

    @Schema(name = "orgId", description = "所属部门Id", type = "Integer")
    private Integer orgId;

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
