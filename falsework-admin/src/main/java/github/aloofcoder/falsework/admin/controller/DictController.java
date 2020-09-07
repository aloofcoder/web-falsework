package github.aloofcoder.falsework.admin.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import github.aloofcoder.falsework.admin.pojo.entity.DictEntity;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.common.util.R;
import github.aloofcoder.falsework.admin.service.IDictService;
import github.aloofcoder.falsework.admin.pojo.dto.DictPageDTO;
import github.aloofcoder.falsework.admin.pojo.vo.DictDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.DictDTO;

/**
 * 数据字典
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@RestController
@RequestMapping("/dicts")
@SecurityRequirement(name = "Authorization")
@Tag(name = "数据字典", description = "数据字典前端控制器")
public class DictController {

    @Autowired
    private IDictService  dictService;

    @Operation(summary = "分页查询数据字典列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DictEntity.class)))
            })
    @GetMapping("/page")
    public R findDictPage(@Valid DictPageDTO pageDTO) {
        PageResult page = dictService.queryDictPage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询数据字典详情", parameters = {
            @Parameter(name = "id", description = "数据字典id", required = true)
    })
    @GetMapping(value = "/{id}")
    public R findDictDetail(@NotBlank(message = "不能为空") @PathVariable("id") Integer id) {
        DictDetailVO vo = dictService.findDictDetail(id);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加数据字典", responses = {
            @ApiResponse(responseCode = "201", description = "添加数据字典返回id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createDict(@Valid @RequestBody DictDTO dictDTO) {
        dictService.createDict(dictDTO);
        return R.ok();
    }

    @Operation(summary = "修改数据字典", parameters = {
            @Parameter(name = "id", description = "数据字典id", in = ParameterIn.PATH)
    })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R updateDict(@PathVariable("id") Integer id, @RequestBody DictDTO dictDTO) {
        dictService.updateDict(id, dictDTO);
        return R.ok();
    }

    @Operation(summary = "批量删除数据字典")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public R delete(@RequestBody Integer[] ids) {
            dictService.deleteDicts(ids);
        return R.ok();
    }
}
