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
import github.aloofcoder.falsework.admin.pojo.entity.UserRoleEntity;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.common.util.R;
import github.aloofcoder.falsework.admin.service.IUserRoleService;
import github.aloofcoder.falsework.admin.pojo.dto.UserRolePageDTO;
import github.aloofcoder.falsework.admin.pojo.vo.UserRoleDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.UserRoleDTO;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@RestController
@RequestMapping("/userRoles")
@Tag(name = "UserRoleController", description = "前端控制器")
public class UserRoleController {

    @Autowired
    private IUserRoleService  userRoleService;

    @Operation(summary = "分页查询列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserRoleEntity.class)))
            })
    @GetMapping("/page")
    public R findUserRolePage(@Valid UserRolePageDTO pageDTO) {
        PageResult page = userRoleService.queryUserRolePage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询详情", parameters = {
            @Parameter(name = "id", description = "id", required = true)
    })
    @GetMapping(value = "/{id}")
    public R findUserRoleDetail(@NotBlank(message = "不能为空") @PathVariable("id") Integer id) {
        UserRoleDetailVO vo = userRoleService.findUserRoleDetail(id);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加", responses = {
            @ApiResponse(responseCode = "201", description = "添加返回id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createUserRole(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        userRoleService.createUserRole(userRoleDTO);
        return R.ok();
    }

    @Operation(summary = "修改", parameters = {
            @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R updateUserRole(@PathVariable("id") Integer id, @RequestBody UserRoleDTO userRoleDTO) {
        userRoleService.updateUserRole(id, userRoleDTO);
        return R.ok();
    }

    @Operation(summary = "批量删除")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public R delete(@RequestBody Integer[] ids) {
            userRoleService.deleteUserRoles(ids);
        return R.ok();
    }
}
