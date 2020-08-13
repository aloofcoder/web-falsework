package github.aloofcoder.generate.pojo.dto;

import lombok.Data;

/**
 * @author hanle
 */
@Data
public class TablePageDTO {

    private int page;

    private int limit;

    private String tableName;
}
