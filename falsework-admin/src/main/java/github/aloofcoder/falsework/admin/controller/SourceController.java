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
import github.aloofcoder.falsework.admin.pojo.entity.SourceEntity;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.common.util.R;
import github.aloofcoder.falsework.admin.service.ISourceService;
import github.aloofcoder.falsework.admin.pojo.dto.SourcePageDTO;
import github.aloofcoder.falsework.admin.pojo.vo.SourceDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.SourceDTO;

/**
 *
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@RestController
@RequestMapping("/sources")
@SecurityRequirement(name = "Authorization")
@Tag(name = "API权限", description = "前端控制器")
public class SourceController {

    @Autowired
    private ISourceService  sourceService;

    @Operation(summary = "分页查询列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = SourceEntity.class)))
            })
    @GetMapping("/page")
    public R findSourcePage(@Valid SourcePageDTO pageDTO) {
        PageResult page = sourceService.querySourcePage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询详情", parameters = {
            @Parameter(name = "id", description = "id", required = true)
    })
    @GetMapping(value = "/{id}")
    public R findSourceDetail(@NotBlank(message = "不能为空") @PathVariable("id") Integer id) {
        SourceDetailVO vo = sourceService.findSourceDetail(id);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加", responses = {
            @ApiResponse(responseCode = "201", description = "添加返回id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createSource(@Valid @RequestBody SourceDTO sourceDTO) {
        sourceService.createSource(sourceDTO);
        return R.ok();
    }

    @Operation(summary = "修改", parameters = {
            @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R updateSource(@PathVariable("id") Integer id, @RequestBody SourceDTO sourceDTO) {
        sourceService.updateSource(id, sourceDTO);
        return R.ok();
    }

    @Operation(summary = "批量删除")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public R delete(@RequestBody Integer[] ids) {
            sourceService.deleteSources(ids);
        return R.ok();
    }
}
