package github.aloofcoder.generate.service;

import github.aloofcoder.generate.pojo.dto.TablePageDTO;
import github.aloofcoder.generate.util.PageResult;

/**
 * @author hanle
 * @date 2020-08-10
 */
public interface ICodeGenerateService {

    PageResult queryTables(TablePageDTO dto);

    byte[] buildCode(String[] tables);
}
