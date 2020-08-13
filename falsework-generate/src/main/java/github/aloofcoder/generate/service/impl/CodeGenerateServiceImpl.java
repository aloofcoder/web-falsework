package github.aloofcoder.generate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import github.aloofcoder.generate.dao.CodeGenerateDao;
import github.aloofcoder.generate.pojo.dto.TablePageDTO;
import github.aloofcoder.generate.service.ICodeGenerateService;
import github.aloofcoder.generate.util.CodeGenerateUtils;
import github.aloofcoder.generate.util.PageResult;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service
public class CodeGenerateServiceImpl implements ICodeGenerateService {

    @Autowired
    private CodeGenerateDao codeGenerateDao;

    @Override
    public PageResult queryTables(TablePageDTO dto) {
        Page<?> page = PageHelper.startPage(dto.getPage(), dto.getLimit());
        List<Map<String, Object>> list = codeGenerateDao.queryList(dto);
        int total = (int) page.getTotal();

        return new PageResult(list, total, dto.getLimit(), dto.getPage());

    }

    public Map<String, String> queryTable(String tableName) {
        return codeGenerateDao.queryTable(tableName);
    }

    public List<Map<String, String>> queryColumns(String tableName) {
        return codeGenerateDao.queryColumns(tableName);
    }

    @Override
    public byte[] buildCode(String[] tables) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tables) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            CodeGenerateUtils.buildCode(table, columns, zip);
        }

        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
