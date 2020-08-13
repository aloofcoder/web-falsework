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
import github.aloofcoder.falsework.admin.pojo.entity.RoleEntity;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.common.util.R;
import github.aloofcoder.falsework.admin.service.IRoleService;
import github.aloofcoder.falsework.admin.pojo.dto.RolePageDTO;
import github.aloofcoder.falsework.admin.pojo.vo.RoleDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.RoleDTO;

/**
 * 
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@RestController
@RequestMapping("/roles")
@Tag(name = "RoleController", description = "前端控制器")
public class RoleController {

    @Autowired
    private IRoleService  roleService;

    @Operation(summary = "分页查询列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = RoleEntity.class)))
            })
    @GetMapping("/page")
    public R findRolePage(@Valid RolePageDTO pageDTO) {
        PageResult page = roleService.queryRolePage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询详情", parameters = {
            @Parameter(name = "roleId", description = "roleId", required = true)
    })
    @GetMapping(value = "/{roleId}")
    public R findRoleDetail(@NotBlank(message = "不能为空") @PathVariable("roleId") Integer roleId) {
        RoleDetailVO vo = roleService.findRoleDetail(roleId);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加", responses = {
            @ApiResponse(responseCode = "201", description = "添加返回roleId",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createRole(@Valid @RequestBody RoleDTO roleDTO) {
        roleService.createRole(roleDTO);
        return R.ok();
    }

    @Operation(summary = "修改", parameters = {
            @Parameter(name = "roleId", description = "roleId", in = ParameterIn.PATH)
    })
    @PutMapping(value = "/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public R updateRole(@PathVariable("roleId") Integer roleId, @RequestBody RoleDTO roleDTO) {
        roleService.updateRole(roleId, roleDTO);
        return R.ok();
    }

    @Operation(summary = "批量删除")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public R delete(@RequestBody Integer[] roleIds) {
            roleService.deleteRoles(roleIds);
        return R.ok();
    }
}
