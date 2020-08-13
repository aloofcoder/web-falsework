package github.aloofcoder.generate.dao;

import github.aloofcoder.generate.pojo.dto.TablePageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Repository
public interface CodeGenerateDao {

    List<Map<String, Object>> queryList(TablePageDTO dto);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
