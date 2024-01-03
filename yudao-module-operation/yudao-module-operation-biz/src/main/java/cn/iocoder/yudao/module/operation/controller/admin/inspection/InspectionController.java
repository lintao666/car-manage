package cn.iocoder.yudao.module.operation.controller.admin.inspection;

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

import cn.iocoder.yudao.module.operation.controller.admin.inspection.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.inspection.InspectionDO;
import cn.iocoder.yudao.module.operation.service.inspection.InspectionService;

@Tag(name = "管理后台 - 年审")
@RestController
@RequestMapping("/operation/inspection")
@Validated
public class InspectionController {

    @Resource
    private InspectionService inspectionService;

    @PostMapping("/create")
    @Operation(summary = "创建年审")
    @PreAuthorize("@ss.hasPermission('operation:inspection:create')")
    public CommonResult<Long> createInspection(@Valid @RequestBody InspectionSaveReqVO createReqVO) {
        return success(inspectionService.createInspection(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新年审")
    @PreAuthorize("@ss.hasPermission('operation:inspection:update')")
    public CommonResult<Boolean> updateInspection(@Valid @RequestBody InspectionSaveReqVO updateReqVO) {
        inspectionService.updateInspection(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除年审")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('operation:inspection:delete')")
    public CommonResult<Boolean> deleteInspection(@RequestParam("id") Long id) {
        inspectionService.deleteInspection(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得年审")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('operation:inspection:query')")
    public CommonResult<InspectionRespVO> getInspection(@RequestParam("id") Long id) {
        InspectionDO inspection = inspectionService.getInspection(id);
        return success(BeanUtils.toBean(inspection, InspectionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得年审分页")
    @PreAuthorize("@ss.hasPermission('operation:inspection:query')")
    public CommonResult<PageResult<InspectionRespVO>> getInspectionPage(@Valid InspectionPageReqVO pageReqVO) {
        PageResult<InspectionDO> pageResult = inspectionService.getInspectionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出年审 Excel")
    @PreAuthorize("@ss.hasPermission('operation:inspection:export')")
    @OperateLog(type = EXPORT)
    public void exportInspectionExcel(@Valid InspectionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionDO> list = inspectionService.getInspectionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "年审.xls", "数据", InspectionRespVO.class,
                        BeanUtils.toBean(list, InspectionRespVO.class));
    }

}