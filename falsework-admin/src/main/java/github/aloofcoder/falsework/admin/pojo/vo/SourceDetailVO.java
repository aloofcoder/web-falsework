package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:00
 */
@Data
@Schema(name = "SourceDetailVO", description = "查询详情返回参数")
public class SourceDetailVO {


    @Schema(name = "sourceName", description = "资源名称", type = "String")
    private String sourceName;

    @Schema(name = "sourceModule", description = "所属模块", type = "String")
    private String sourceModule;

    @Schema(name = "method", description = "资源请求方法", type = "Enum")
    private Enum method;

    @Schema(name = "sourceUrl", description = "资源url", type = "String")
    private String sourceUrl;

    @Schema(name = "sourceCode", description = "系统生成的资源唯一号", type = "String")
    private String sourceCode;

    @Schema(name = "status", description = "(1正常2禁用)", type = "Integer")
    private Integer status;




    @Schema(name = "editTme", description = "修改时间", type = "Date")
    private Date editTme;
}
