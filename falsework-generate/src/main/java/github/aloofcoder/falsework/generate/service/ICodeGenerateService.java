package github.aloofcoder.falsework.generate.service;

import github.aloofcoder.falsework.generate.pojo.dto.TablePageDTO;
import github.aloofcoder.falsework.generate.util.PageResult;

/**
 * @author hanle
 * @date 2020-08-10
 */
public interface ICodeGenerateService {

    PageResult queryTables(TablePageDTO dto);

    byte[] buildCode(String[] tables);
}
