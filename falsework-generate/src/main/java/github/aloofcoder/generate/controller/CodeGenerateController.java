package github.aloofcoder.generate.controller;

import github.aloofcoder.generate.pojo.dto.GenerateCodeDTO;
import github.aloofcoder.generate.pojo.dto.TablePageDTO;
import github.aloofcoder.generate.service.ICodeGenerateService;
import github.aloofcoder.generate.util.PageResult;
import github.aloofcoder.generate.util.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 数据字典
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-12 23:02:52
 */
@Slf4j
@RestController
@RequestMapping("/sys/generate")
@Tag(name = "代码生成" , description = "代码生成前端控制器")
public class CodeGenerateController {

    @Autowired
    private ICodeGenerateService codeGenerateService;


    @GetMapping("/page")
    public R queryTables(TablePageDTO dto) {
        PageResult page = codeGenerateService.queryTables(dto);
        return R.ok().put("data" , page);
    }

    @GetMapping("/build")
    public void buildCode(@Valid GenerateCodeDTO dto, HttpServletResponse response) throws IOException {
        byte[] data = codeGenerateService.buildCode(dto.getTables().split(","));
        response.reset();
        response.setHeader("Content-Disposition" , "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length" , "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

}
