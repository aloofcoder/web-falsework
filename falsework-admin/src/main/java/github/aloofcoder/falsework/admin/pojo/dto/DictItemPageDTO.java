package github.aloofcoder.falsework.admin.pojo.dto;

import github.aloofcoder.falsework.common.pojo.dto.PageDTO;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:01
 */
@Data
@ParameterObject
public class DictItemPageDTO extends PageDTO {

    @NotNull(message = "请选择字典名称")
    @Min(value = 1, message = "字典Id必须大于等于1")
    private Integer dictId;
}
