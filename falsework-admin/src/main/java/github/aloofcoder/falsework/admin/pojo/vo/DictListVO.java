package github.aloofcoder.falsework.admin.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "DictListVO", description = "数据字典列表")
public class DictListVO {

    @Schema(name = "id", description = "id", type = "Integer")
    private Integer id;

    @Schema(name = "dictName", description = "字典名称", type = "String")
    private String dictName;
}
