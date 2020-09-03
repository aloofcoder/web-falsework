package github.aloofcoder.falsework.admin.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import github.aloofcoder.falsework.admin.pojo.entity.SettingEntity;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.common.util.R;
import github.aloofcoder.falsework.admin.service.ISettingService;
import github.aloofcoder.falsework.admin.pojo.dto.SettingPageDTO;
import github.aloofcoder.falsework.admin.pojo.vo.SettingDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.SettingDTO;

/**
 * 系统全局设置表
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@RestController
@RequestMapping("/settings")
@Tag(name = "系统设置", description = "系统全局设置表前端控制器")
public class SettingController {

    @Autowired
    private ISettingService  settingService;

    @Operation(summary = "分页查询系统全局设置表列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = SettingEntity.class)))
            })
    @GetMapping("/page")
    public R findSettingPage(@Valid SettingPageDTO pageDTO) {
        PageResult page = settingService.querySettingPage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询系统全局设置表详情", parameters = {
            @Parameter(name = "id", description = "系统全局设置表id", required = true)
    })
    @GetMapping(value = "/{id}")
    public R findSettingDetail(@NotBlank(message = "不能为空") @PathVariable("id") Integer id) {
        SettingDetailVO vo = settingService.findSettingDetail(id);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加系统全局设置表", responses = {
            @ApiResponse(responseCode = "201", description = "添加系统全局设置表返回id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createSetting(@Valid @RequestBody SettingDTO settingDTO) {
        settingService.createSetting(settingDTO);
        return R.ok();
    }

    @Operation(summary = "修改系统全局设置表", parameters = {
            @Parameter(name = "id", description = "系统全局设置表id", in = ParameterIn.PATH)
    })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R updateSetting(@PathVariable("id") Integer id, @RequestBody SettingDTO settingDTO) {
        settingService.updateSetting(id, settingDTO);
        return R.ok();
    }

    @Operation(summary = "批量删除系统全局设置表")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public R delete(@RequestBody Integer[] ids) {
            settingService.deleteSettings(ids);
        return R.ok();
    }
}
