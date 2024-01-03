package cn.iocoder.yudao.module.operation.controller.admin.trafficaccident;

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

import cn.iocoder.yudao.module.operation.controller.admin.trafficaccident.vo.*;
import cn.iocoder.yudao.module.operation.dal.dataobject.trafficaccident.TrafficAccidentDO;
import cn.iocoder.yudao.module.operation.service.trafficaccident.TrafficAccidentService;

@Tag(name = "管理后台 - 交通事故")
@RestController
@RequestMapping("/operation/traffic-accident")
@Validated
public class TrafficAccidentController {

    @Resource
    private TrafficAccidentService trafficAccidentService;

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

}