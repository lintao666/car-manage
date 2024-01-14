package cn.iocoder.yudao.module.operation.controller.admin.insurance;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.operation.controller.admin.insurance.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.insurance.InsuranceDO;
import cn.iocoder.yudao.module.operation.service.insurance.InsuranceService;
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

@Tag(name = "管理后台 - 保单")
@RestController
@RequestMapping("/operation/insurance")
@Validated
public class InsuranceController {

    @Resource
    private InsuranceService insuranceService;
    @Resource
    private VehicleApi vehicleApi;

    @PostMapping("/create")
    @Operation(summary = "创建保单")
    @PreAuthorize("@ss.hasPermission('operation:insurance:create')")
    public CommonResult<Long> createInsurance(@Valid @RequestBody InsuranceSaveReqVO createReqVO) {
        return success(insuranceService.createInsurance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新保单")
    @PreAuthorize("@ss.hasPermission('operation:insurance:update')")
    public CommonResult<Boolean> updateInsurance(@Valid @RequestBody InsuranceSaveReqVO updateReqVO) {
        insuranceService.updateInsurance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除保单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('operation:insurance:delete')")
    public CommonResult<Boolean> deleteInsurance(@RequestParam("id") Long id) {
        insuranceService.deleteInsurance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得保单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('operation:insurance:query')")
    public CommonResult<InsuranceRespVO> getInsurance(@RequestParam("id") Long id) {
        InsuranceDO insurance = insuranceService.getInsurance(id);
        return success(BeanUtils.toBean(insurance, InsuranceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得保单分页")
    @PreAuthorize("@ss.hasPermission('operation:insurance:query')")
    public CommonResult<PageResult<InsuranceRespVO>> getInsurancePage(@Valid InsurancePageReqVO pageReqVO) {
        PageResult<InsuranceDO> pageResult = insuranceService.getInsurancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InsuranceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出保单 Excel")
    @PreAuthorize("@ss.hasPermission('operation:insurance:export')")
    @OperateLog(type = EXPORT)
    public void exportInsuranceExcel(@Valid InsurancePageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InsuranceDO> list = insuranceService.getInsurancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "保单.xls", "数据", InsuranceRespVO.class,
                BeanUtils.toBean(list, InsuranceRespVO.class));
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入保险记录模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<InsuranceImportVO> list = Arrays.asList(
                InsuranceImportVO.builder()//.vehicleMask("14623")
                        .carNumber("川ADU6291")
                        .insuranceType(1)
                        .insuranceCompany("保险公司")
                        .insuranceFee(100d)
                        .insuranceNumber("保单号sdsfas")
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now())
                        .build(),
                InsuranceImportVO.builder()//.vehicleMask("14623")
                        .carNumber("川ADU6291")
                        .insuranceType(1)
                        .insuranceCompany("保险公司")
                        .insuranceFee(100d)
                        .insuranceNumber("保单号sdsfas")
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now())
                        .build()
        );
        ExcelUtils.write(response, "车辆交强险导入模板.xls", "车辆交强险记录", InsuranceImportVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入车辆交强险 Excel")
    @PreAuthorize("@ss.hasPermission('operation:repair-vehicle:import')")
    @OperateLog(type = IMPORT)
    public CommonResult<InsuranceImportRespVO> importExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {
        InsuranceListener listener = new InsuranceListener(insuranceService, vehicleApi);
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(file.getInputStream(), InsuranceImportVO.class, listener)
                .sheet().doRead();
        return success(listener.getImportResp());
    }

}