package github.aloofcoder.falsework.generate.dao;

import github.aloofcoder.falsework.generate.pojo.dto.TablePageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author hanle
 */
@Repository
public interface CodeGenerateDao {

    List<Map<String, Object>> queryList(TablePageDTO dto);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
