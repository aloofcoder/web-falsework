package github.aloofcoder.falsework.admin.controller;


import github.aloofcoder.falsework.admin.pojo.dto.RoleDTO;
import github.aloofcoder.falsework.admin.pojo.dto.RolePageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.RoleEntity;
import github.aloofcoder.falsework.admin.pojo.vo.MenuListVO;
import github.aloofcoder.falsework.admin.pojo.vo.RoleDetailVO;
import github.aloofcoder.falsework.admin.pojo.vo.RoleListVO;
import github.aloofcoder.falsework.admin.service.IMenuService;
import github.aloofcoder.falsework.admin.service.IRoleMenuService;
import github.aloofcoder.falsework.admin.service.IRoleService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.common.util.R;
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
import java.util.List;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:55
 */
@RestController
@RequestMapping("/roles")
@SecurityRequirement(name = "Authorization")
@Tag(name = "用户角色", description = "前端控制器")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IMenuService menuService;

    @Operation(summary = "分页查询列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = RoleEntity.class)))
            })
    @GetMapping("/page")
    public R findRolePage(@Valid RolePageDTO pageDTO) {
        PageResult page = roleService.queryRolePage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询角色列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = RoleListVO.class)))
            })
    @GetMapping("/list")
    public R findRoleList() {
        List<RoleListVO> list = roleService.findRoleList();
        return R.ok().put("data", list);
    }

    @Operation(summary = "查询角色授权菜单及全部菜单", parameters = {
            @Parameter(name = "roleId", description = "roleId", required = true)
    })
    @GetMapping(value = "/menus/{roleId}")
    public R findRoleMenus(@NotBlank(message = "不能为空") @PathVariable("roleId") Integer roleId) {
        List<MenuListVO> roleMenus = menuService.findRoleMenuListByRoleId(roleId);
        List<MenuListVO> menuList = menuService.findMenuList(true);
        return R.ok().put("roleMenus", roleMenus).put("menuList", menuList);
    }

    @Operation(summary = "角色授权菜单", parameters = {
            @Parameter(name = "roleId", description = "roleId", required = true, in = ParameterIn.PATH),
            @Parameter(name = "menuIds", description = "菜单列表", required = true, in = ParameterIn.DEFAULT),
    })
    @PostMapping(value = "/menus/{roleId}")
    public R roleAuthMenus(@NotBlank(message = "不能为空") @PathVariable("roleId") Integer roleId,
                           @RequestBody Integer[] menuIds) {
        roleService.roleAuthMenus(roleId, menuIds);
        return R.ok();
    }

    @Operation(summary = "查询详情", parameters = {
            @Parameter(name = "roleId", description = "roleId", required = true)
    })
    @GetMapping(value = "/{roleId}")
    public R findRoleDetail(@NotBlank(message = "不能为空") @PathVariable("roleId") Integer roleId) {
        RoleDetailVO vo = roleService.findRoleDetail(roleId);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加角色", responses = {
            @ApiResponse(responseCode = "201", description = "添加返回roleId",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createRole(@Valid @RequestBody RoleDTO roleDTO) {
        roleService.createRole(roleDTO);
        return R.ok();
    }

    @Operation(summary = "修改角色", parameters = {
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
