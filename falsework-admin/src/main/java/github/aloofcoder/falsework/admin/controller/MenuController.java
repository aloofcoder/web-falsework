package github.aloofcoder.falsework.admin.controller;


import github.aloofcoder.falsework.admin.pojo.dto.MenuDTO;
import github.aloofcoder.falsework.admin.pojo.dto.MenuPageDTO;
import github.aloofcoder.falsework.admin.pojo.dto.MenuTreeDTO;
import github.aloofcoder.falsework.admin.pojo.entity.MenuEntity;
import github.aloofcoder.falsework.admin.pojo.vo.MenuDetailVO;
import github.aloofcoder.falsework.admin.service.IMenuService;
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
import java.util.List;

/**
 * 系统菜单
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-20 14:48:53
 */
@RestController
@RequestMapping("/menus")
@Tag(name = "系统菜单", description = "系统菜单前端控制器")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @Operation(summary = "分页查询系统菜单列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MenuEntity.class)))
            })
    @GetMapping("/page")
    public R findMenuPage(@Valid MenuPageDTO pageDTO) {
        PageResult page = menuService.queryMenuPage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询树状菜单",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MenuEntity.class)))
            })
    @GetMapping("/tree")
    public R findMenuTree(@Valid MenuTreeDTO menuTreeDTO) {
        List<MenuEntity> list = menuService.findMenuTree(menuTreeDTO);
        return R.ok().put("data", list);
    }

    @Operation(summary = "查询系统菜单详情", parameters = {
            @Parameter(name = "id", description = "系统菜单id", required = true)
    })
    @GetMapping(value = "/{id}")
    public R findMenuDetail(@NotBlank(message = "不能为空") @PathVariable("id") Integer id) {
        MenuDetailVO vo = menuService.findMenuDetail(id);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加系统菜单", responses = {
            @ApiResponse(responseCode = "201", description = "添加系统菜单返回id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createMenu(@Valid @RequestBody MenuDTO menuDTO) {
        menuService.createMenu(menuDTO);
        return R.ok();
    }

    @Operation(summary = "修改系统菜单", parameters = {
            @Parameter(name = "id", description = "系统菜单id", in = ParameterIn.PATH)
    })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R updateMenu(@PathVariable("id") Integer id, @RequestBody MenuDTO menuDTO) {
        menuService.updateMenu(id, menuDTO);
        return R.ok();
    }

    @Operation(summary = "批量删除系统菜单")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public R delete(@RequestBody Integer[] ids) {
        menuService.deleteMenus(ids);
        return R.ok();
    }
}
