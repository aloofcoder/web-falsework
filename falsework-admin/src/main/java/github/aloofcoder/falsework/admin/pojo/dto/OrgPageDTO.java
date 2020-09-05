package github.aloofcoder.falsework.admin.pojo.dto;

import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;
import github.aloofcoder.falsework.common.pojo.dto.PageDTO;

/**
 * 公司组织
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 15:51:00
 */
@Data
@ParameterObject
public class OrgPageDTO extends PageDTO {

    private String condition;
}
