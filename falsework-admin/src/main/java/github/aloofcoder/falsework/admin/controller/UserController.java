package github.aloofcoder.falsework.admin.controller;


import github.aloofcoder.falsework.admin.pojo.dto.UserDTO;
import github.aloofcoder.falsework.admin.pojo.dto.UserPageDTO;
import github.aloofcoder.falsework.admin.pojo.entity.MenuEntity;
import github.aloofcoder.falsework.admin.pojo.entity.UserEntity;
import github.aloofcoder.falsework.admin.pojo.vo.MenuAuthListVO;
import github.aloofcoder.falsework.admin.pojo.vo.RoleListVO;
import github.aloofcoder.falsework.admin.pojo.vo.UserDetailVO;
import github.aloofcoder.falsework.admin.service.IMenuService;
import github.aloofcoder.falsework.admin.service.IRoleService;
import github.aloofcoder.falsework.admin.service.IUserService;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.common.util.R;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@RestController
@RequestMapping("/users")
@Tag(name = "系统用户", description = "前端控制器")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    @PostMapping("/login")
    public R login() {
        Map<String, String> token = new HashMap<>(1);
        token.put("token", "admin-token");
        return R.ok().put("data", token);
    }

    @GetMapping("/info")
    public R info() {
        Map<String, Object> info = new HashMap<>(4);
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        info.put("roles", roles);
        info.put("introduction", "I am a super administrator");
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", "Super Admin");
        List<MenuEntity> auths = menuService.findAuth();
        List<MenuAuthListVO> menuList = menuService.findAuthMenu();
        return R.ok().put("data", info).put("auths", auths).put("menus", menuList);
    }

    @PostMapping("/logout")
    public R logout() {
        return R.ok();
    }

    @Operation(summary = "分页查询列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserEntity.class)))
            })
    @GetMapping("/page")
    public R findUserPage(@Valid UserPageDTO pageDTO) {
        PageResult page = userService.queryUserPage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询详情", parameters = {
            @Parameter(name = "userNum", description = "用户编号", required = true)
    })
    @GetMapping(value = "/{userNum}")
    public R findUserDetail(@NotBlank(message = "用户编号不能为空") @PathVariable("userNum") String userNum) {
        UserDetailVO vo = userService.findUserDetail(userNum);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加", responses = {
            @ApiResponse(responseCode = "201", description = "添加返回id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.createUser(userDTO);
        return R.ok();
    }

    @Operation(summary = "修改用户", parameters = {
            @Parameter(name = "userNum", description = "用户编号", in = ParameterIn.PATH)
    })
    @PutMapping(value = "/{userNum}")
    @ResponseStatus(HttpStatus.OK)
    public R updateUser(@PathVariable("userNum") String userNum, @RequestBody @Valid UserDTO userDTO) {
        userService.updateUser(userNum, userDTO);
        return R.ok();
    }

    @Operation(summary = "批量删除")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public R delete(@RequestBody String[] userNums) {
        userService.deleteUsers(userNums);
        return R.ok();
    }

    @Operation(summary = "查询用户角色", parameters = {
            @Parameter(name = "userNum", description = "用户编号", required = true)
    }, responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserEntity.class)))
    })
    @GetMapping(value = "/roles/{userNum}")
    public R findUserRoles(@NotBlank(message = "不能为空") @PathVariable("userNum") String userNum) {
        List<Integer> roleIds = userService.findUserRoles(userNum);
        List<RoleListVO> roleList = roleService.findRoleList();
        return R.ok().put("userRoles", roleIds).put("roleList", roleList);
    }

    @Operation(summary = "用户角色分配")
    @PostMapping(value = "/roles/{userNum}")
    public R userRoleAssign(@PathVariable("userNum") String userNum, @RequestBody Integer[] roleIds) {
        userService.userRoleAssign(userNum, roleIds);
        return R.ok();
    }

}
