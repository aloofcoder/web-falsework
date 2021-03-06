package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 数据字典
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:00
 */
@Data
@Schema(name = "DictDetailVO", description = "查询数据字典详情返回参数")
public class DictDetailVO {

    @Schema(name = "id", description = "id", type = "Integer")
    private Integer id;

    @Schema(name = "dictName", description = "字典名称", type = "String")
    private String dictName;

    @Schema(name = "dictMark", description = "字典标记", type = "String")
    private String dictMark;
}
