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
@Schema(name = "SourceDTO", description = "添加参数")
public class SourceDTO {


    @Schema(name = "sourceName", description = "资源名称", type = "String")
    private String sourceName;

    @Schema(name = "sourceModule", description = "所属模块", type = "String")
    private String sourceModule;

    @Schema(name = "method", description = "资源请求方法", type = "Enum")
    private String method;

    @Schema(name = "sourceUrl", description = "资源url", type = "String")
    private String sourceUrl;

    @Schema(name = "sourceCode", description = "系统生成的资源唯一号", type = "String")
    private String sourceCode;

    @Schema(name = "status", description = "(1正常2禁用)", type = "Integer")
    private Integer status;

    @Schema(name = "createBy", description = "创建人", type = "String")
    private String createBy;

    @Schema(name = "editBy", description = "修改人", type = "String")
    private String editBy;

    @Schema(name = "createTime", description = "创建时间", type = "Date")
    private Date createTime;

    @Schema(name = "editTme", description = "修改时间", type = "Date")
    private Date editTme;
}
