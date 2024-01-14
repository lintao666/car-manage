package cn.iocoder.yudao.module.operation.controller.admin.inspection;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.inspection.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.inspection.InspectionDO;
import cn.iocoder.yudao.module.operation.service.inspection.InspectionService;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.IMPORT;

@Tag(name = "管理后台 - 年审")
@RestController
@RequestMapping("/operation/inspection")
@Validated
public class InspectionController {

    @Resource
    private InspectionService inspectionService;
    @Resource
    private VehicleApi vehicleApi;

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

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入车辆年审记录模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<InspectionImportVO> list = Arrays.asList(
                InspectionImportVO.builder().vehicleMask("14623").carNumber("川ADU6291")
                        .inspectionDate(LocalDate.of(2023, 4, 1))
                        .inspectionEndDate(LocalDate.of(2023, 1, 12))
                        .informInspectionDate(LocalDate.of(2023, 4, 1)).informUser("保修人11")
                        .inspectionType(1)
                        .inspectionAddress("成都市三宏汽车技术有限公司")
                        .planInspectionDate(LocalDate.of(2023, 1, 12))
                        .driverName("司机11")
                        .build(),
                InspectionImportVO.builder().vehicleMask("14623").carNumber("川ADU6291")
                        .inspectionDate(LocalDate.of(2023, 4, 1))
                        .inspectionEndDate(LocalDate.of(2023, 1, 12))
                        .informInspectionDate(LocalDate.of(2023, 4, 1)).informUser("保修人11")
                        .inspectionType(1)
                        .inspectionAddress("成都市三宏汽车技术有限公司")
                        .planInspectionDate(LocalDate.of(2023, 1, 12))
                        .driverName("司机22")
                        .build()
        );
        ExcelUtils.write(response, "车辆年审导入模板.xls", "车辆年审", InspectionImportVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入维修 Excel")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:import')")
    @OperateLog(type = IMPORT)
    public CommonResult<InspectionImportRespVO> importExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {
        InspectionListener listener = new InspectionListener(inspectionService, vehicleApi);
        EasyExcel.read(file.getInputStream(), InspectionImportVO.class, listener)
                .sheet().doRead();
        return success(listener.getImportResp());
    }

}