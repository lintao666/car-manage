package cn.iocoder.yudao.module.operation.controller.admin.trafficaccident;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.business.api.driver.DriverApi;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficaccident.TrafficAccidentDO;
import cn.iocoder.yudao.module.operation.service.trafficaccident.TrafficAccidentService;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.IMPORT;

@Tag(name = "管理后台 - 交通事故")
@RestController
@RequestMapping("/operation/traffic-accident")
@Validated
@AllArgsConstructor
public class TrafficAccidentController {

    private final TrafficAccidentService trafficAccidentService;
    private final VehicleApi vehicleApi;
    private final DriverApi driverApi;

    @PostMapping("/create")
    @Operation(summary = "创建交通事故")
    @PreAuthorize("@ss.hasPermission('operation:traffic-accident:create')")
    public CommonResult<Long> createTrafficAccident(@Valid @RequestBody TrafficAccidentSaveReqVO createReqVO) {
        return success(trafficAccidentService.createTrafficAccident(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新交通事故")
    @PreAuthorize("@ss.hasPermission('operation:traffic-accident:update')")
    public CommonResult<Boolean> updateTrafficAccident(@Valid @RequestBody TrafficAccidentSaveReqVO updateReqVO) {
        trafficAccidentService.updateTrafficAccident(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除交通事故")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('operation:traffic-accident:delete')")
    public CommonResult<Boolean> deleteTrafficAccident(@RequestParam("id") Long id) {
        trafficAccidentService.deleteTrafficAccident(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得交通事故")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('operation:traffic-accident:query')")
    public CommonResult<TrafficAccidentRespVO> getTrafficAccident(@RequestParam("id") Long id) {
        TrafficAccidentDO trafficAccident = trafficAccidentService.getTrafficAccident(id);
        return success(BeanUtils.toBean(trafficAccident, TrafficAccidentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得交通事故分页")
    @PreAuthorize("@ss.hasPermission('operation:traffic-accident:query')")
    public CommonResult<PageResult<TrafficAccidentRespVO>> getTrafficAccidentPage(@Valid TrafficAccidentPageReqVO pageReqVO) {
        PageResult<TrafficAccidentDO> pageResult = trafficAccidentService.getTrafficAccidentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TrafficAccidentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出交通事故 Excel")
    @PreAuthorize("@ss.hasPermission('operation:traffic-accident:export')")
    @OperateLog(type = EXPORT)
    public void exportTrafficAccidentExcel(@Valid TrafficAccidentPageReqVO pageReqVO,
                                           HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TrafficAccidentDO> list = trafficAccidentService.getTrafficAccidentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "交通事故.xls", "数据", TrafficAccidentRespVO.class,
                BeanUtils.toBean(list, TrafficAccidentRespVO.class));
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入交通事故模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<TrafficAccidentImportVO> list = Arrays.asList(
                TrafficAccidentImportVO.builder().vehicleMask("14623").carNumber("川ADU6291")
                        .driverName(("司机11"))
                        .accidentDate(LocalDate.of(2023, 4, 1))
                        .accidentDesc("事故简况")
                        .handleProcess("处理情况")
                        .identificationRecordNum("认定书号")
                        .identificationDept("认定部门")
                        .injuredDeadDesc("伤亡情况")
                        .insuranceDate1(LocalDate.of(2023, 4, 1))
                        .settlementDate(LocalDate.of(2023, 4, 1))
                        .insurancePay1(500d)
                        .totalPay(5000d)
                        .build(),
                TrafficAccidentImportVO.builder().vehicleMask("14623").carNumber("川ADU6291")
                        .driverName(("司机11"))
                        .accidentDate(LocalDate.of(2023, 4, 1))
                        .accidentDesc("事故简况")
                        .handleProcess("处理情况")
                        .identificationRecordNum("认定书号")
                        .identificationDept("认定部门")
                        .injuredDeadDesc("伤亡情况")
                        .insuranceDate1(LocalDate.of(2023, 4, 1))
                        .settlementDate(LocalDate.of(2023, 4, 1))
                        .insurancePay1(500d)
                        .totalPay(5000d)
                        .build()
        );
        ExcelUtils.write(response, "车辆交通事故导入模板.xls", "车辆交通事故", TrafficAccidentImportVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入维修 Excel")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:import')")
    @OperateLog(type = IMPORT)
    public CommonResult<TrafficAccidentImportRespVO> importExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {
        TrafficAccidentListener listener = new TrafficAccidentListener(trafficAccidentService, vehicleApi, driverApi);
        EasyExcel.read(file.getInputStream(), TrafficAccidentImportVO.class, listener)
                .sheet().doRead();
        return success(listener.getImportResp());
    }

}