package cn.iocoder.yudao.module.operation.controller.admin.trafficviolation;

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

import cn.iocoder.yudao.module.operation.controller.admin.trafficviolation.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficviolation.TrafficViolationDO;
import cn.iocoder.yudao.module.operation.service.trafficviolation.TrafficViolationService;

@Tag(name = "管理后台 - 交通违法")
@RestController
@RequestMapping("/operation/traffic-violation")
@Validated
public class TrafficViolationController {

    @Resource
    private TrafficViolationService trafficViolationService;

    @PostMapping("/create")
    @Operation(summary = "创建交通违法")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:create')")
    public CommonResult<Long> createTrafficViolation(@Valid @RequestBody TrafficViolationSaveReqVO createReqVO) {
        return success(trafficViolationService.createTrafficViolation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新交通违法")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:update')")
    public CommonResult<Boolean> updateTrafficViolation(@Valid @RequestBody TrafficViolationSaveReqVO updateReqVO) {
        trafficViolationService.updateTrafficViolation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除交通违法")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:delete')")
    public CommonResult<Boolean> deleteTrafficViolation(@RequestParam("id") Long id) {
        trafficViolationService.deleteTrafficViolation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得交通违法")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:query')")
    public CommonResult<TrafficViolationRespVO> getTrafficViolation(@RequestParam("id") Long id) {
        TrafficViolationDO trafficViolation = trafficViolationService.getTrafficViolation(id);
        return success(BeanUtils.toBean(trafficViolation, TrafficViolationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得交通违法分页")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:query')")
    public CommonResult<PageResult<TrafficViolationRespVO>> getTrafficViolationPage(@Valid TrafficViolationPageReqVO pageReqVO) {
        PageResult<TrafficViolationDO> pageResult = trafficViolationService.getTrafficViolationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TrafficViolationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出交通违法 Excel")
    @PreAuthorize("@ss.hasPermission('operation:traffic-violation:export')")
    @OperateLog(type = EXPORT)
    public void exportTrafficViolationExcel(@Valid TrafficViolationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TrafficViolationDO> list = trafficViolationService.getTrafficViolationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "交通违法.xls", "数据", TrafficViolationRespVO.class,
                        BeanUtils.toBean(list, TrafficViolationRespVO.class));
    }

}