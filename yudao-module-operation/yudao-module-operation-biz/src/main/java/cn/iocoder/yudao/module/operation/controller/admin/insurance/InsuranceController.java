package cn.iocoder.yudao.module.operation.controller.admin.insurance;

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

import cn.iocoder.yudao.module.operation.controller.admin.insurance.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.insurance.InsuranceDO;
import cn.iocoder.yudao.module.operation.service.insurance.InsuranceService;

@Tag(name = "管理后台 - 保单")
@RestController
@RequestMapping("/operation/insurance")
@Validated
public class InsuranceController {

    @Resource
    private InsuranceService insuranceService;

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

}