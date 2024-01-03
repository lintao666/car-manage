package cn.iocoder.yudao.module.operation.controller.admin.maintain;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.operation.controller.admin.maintain.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.maintain.MaintainDO;
import cn.iocoder.yudao.module.operation.service.maintain.MaintainService;

@Tag(name = "管理后台 - 保养/二级维护")
@RestController
@RequestMapping("/operation/maintain")
@Validated
public class MaintainController {

    @Resource
    private MaintainService maintainService;

    @PostMapping("/create")
    @Operation(summary = "创建保养/二级维护")
    @PreAuthorize("@ss.hasPermission('operation:maintain:create')")
    public CommonResult<Long> createMaintain(@Valid @RequestBody MaintainSaveReqVO createReqVO) {
        return success(maintainService.createMaintain(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新保养/二级维护")
    @PreAuthorize("@ss.hasPermission('operation:maintain:update')")
    public CommonResult<Boolean> updateMaintain(@Valid @RequestBody MaintainSaveReqVO updateReqVO) {
        maintainService.updateMaintain(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除保养/二级维护")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('operation:maintain:delete')")
    public CommonResult<Boolean> deleteMaintain(@RequestParam("id") Long id) {
        maintainService.deleteMaintain(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得保养/二级维护")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('operation:maintain:query')")
    public CommonResult<MaintainRespVO> getMaintain(@RequestParam("id") Long id) {
        MaintainDO maintain = maintainService.getMaintain(id);
        return success(BeanUtils.toBean(maintain, MaintainRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得保养/二级维护分页")
    @PreAuthorize("@ss.hasPermission('operation:maintain:query')")
    public CommonResult<PageResult<MaintainRespVO>> getMaintainPage(@Valid MaintainPageReqVO pageReqVO) {
        PageResult<MaintainDO> pageResult = maintainService.getMaintainPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintainRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出保养/二级维护 Excel")
    @PreAuthorize("@ss.hasPermission('operation:maintain:export')")
    @OperateLog(type = EXPORT)
    public void exportMaintainExcel(@Valid MaintainPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintainDO> list = maintainService.getMaintainPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "保养/二级维护.xls", "数据", MaintainRespVO.class,
                        BeanUtils.toBean(list, MaintainRespVO.class));
    }

}