package github.aloofcoder.falsework.admin.controller;


import github.aloofcoder.falsework.admin.pojo.vo.OrgListVO;
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
import github.aloofcoder.falsework.admin.pojo.entity.OrgEntity;
import github.aloofcoder.falsework.common.util.PageResult;
import github.aloofcoder.falsework.common.util.R;
import github.aloofcoder.falsework.admin.service.IOrgService;
import github.aloofcoder.falsework.admin.pojo.dto.OrgPageDTO;
import github.aloofcoder.falsework.admin.pojo.vo.OrgDetailVO;
import github.aloofcoder.falsework.admin.pojo.dto.OrgDTO;

import java.util.List;

/**
 * 公司组织
 *
 * @author hanle
 * @email hanl1946@163.com
 * @date 2020-08-14 01:30:54
 */
@RestController
@RequestMapping("/orgs")
@Tag(name = "公司组织", description = "公司组织前端控制器")
public class OrgController {

    @Autowired
    private IOrgService  orgService;

    @Operation(summary = "分页查询公司组织列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OrgEntity.class)))
            })
    @GetMapping("/page")
    public R findOrgPage(@Valid OrgPageDTO pageDTO) {
        PageResult page = orgService.queryOrgPage(pageDTO);
        return R.ok().put("data", page);
    }

    @Operation(summary = "查询全部组织列表",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = OrgListVO.class)))
            })
    @GetMapping("/list")
    public R findOrgList() {
        List<OrgListVO> list = orgService.findOrgList();
        return R.ok().put("data", list);
    }

    @Operation(summary = "查询公司组织详情", parameters = {
            @Parameter(name = "id", description = "公司组织id", required = true)
    })
    @GetMapping(value = "/{id}")
    public R findOrgDetail(@NotBlank(message = "不能为空") @PathVariable("id") Integer id) {
        OrgDetailVO vo = orgService.findOrgDetail(id);
        return R.ok().put("data", vo);
    }

    @Operation(summary = "添加公司组织", responses = {
            @ApiResponse(responseCode = "201", description = "添加公司组织返回id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createOrg(@Valid @RequestBody OrgDTO orgDTO) {
        orgService.createOrg(orgDTO);
        return R.ok();
    }

    @Operation(summary = "修改公司组织", parameters = {
            @Parameter(name = "id", description = "公司组织id", in = ParameterIn.PATH)
    })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public R updateOrg(@PathVariable("id") Integer id, @RequestBody OrgDTO orgDTO) {
        orgService.updateOrg(id, orgDTO);
        return R.ok();
    }

    @Operation(summary = "批量删除公司组织")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public R delete(@RequestBody Integer[] ids) {
            orgService.deleteOrgs(ids);
        return R.ok();
    }
}
