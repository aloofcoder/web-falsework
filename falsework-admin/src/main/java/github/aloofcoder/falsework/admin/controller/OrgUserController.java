package github.aloofcoder.falsework.admin.controller;


import io.swagger.v3.oas.annotations.Hidden;
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
import github.aloofcoder.falsework.admin.pojo.entity.OrgUserEntity;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.common.util.R;
import github.aloofcoder.falsework.admin.service.IOrgUserService;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserPageDTO;
import github.aloofcoder.falsework.admin.pojo.vo.OrgUserDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgUserDTO;

/**
 *
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@RestController
@RequestMapping("/orgUsers")
@Hidden
@Tag(name = "OrgUserController", description = "前端控制器")
public class OrgUserController {

    @Autowired
    private IOrgUserService  orgUserService;

    @Operation(summary = "分页查询列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OrgUserEntity.class)))
            })
    @GetMapping("/page")
    public R findOrgUserPage(@Valid OrgUserPageDTO pageDTO) {
        PageResult page = orgUserService.queryOrgUserPage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询详情", parameters = {
            @Parameter(name = "id", description = "id", required = true)
    })
    @GetMapping(value = "/{id}")
    public R findOrgUserDetail(@NotBlank(message = "不能为空") @PathVariable("id") Integer id) {
        OrgUserDetailVO vo = orgUserService.findOrgUserDetail(id);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加", responses = {
            @ApiResponse(responseCode = "201", description = "添加返回id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createOrgUser(@Valid @RequestBody OrgUserDTO orgUserDTO) {
        orgUserService.createOrgUser(orgUserDTO);
        return R.ok();
    }

    @Operation(summary = "修改", parameters = {
            @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R updateOrgUser(@PathVariable("id") Integer id, @RequestBody OrgUserDTO orgUserDTO) {
        orgUserService.updateOrgUser(id, orgUserDTO);
        return R.ok();
    }

    @Operation(summary = "批量删除")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public R delete(@RequestBody Integer[] ids) {
            orgUserService.deleteOrgUsers(ids);
        return R.ok();
    }
}
