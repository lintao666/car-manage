package cn.iocoder.yudao.module.operation.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * operation 错误码枚举类
 * <p>
 * operation 系统，使用 1-004-000-000 段
 */
public interface ErrorCodeConstants {
    ErrorCode INSPECTION_NOT_EXISTS = new ErrorCode(1_004_000_001, "年审记录不存在");
    ErrorCode INSURANCE_NOT_EXISTS = new ErrorCode(1_004_000_002, "保单记录不存在");
    ErrorCode MAINTAIN_NOT_EXISTS = new ErrorCode(1_004_000_003, "保养记录不存在");
    ErrorCode REPAIR_VEHICLE_NOT_EXISTS = new ErrorCode(1_004_000_004, "维修不存在");
    ErrorCode TRAFFIC_ACCIDENT_NOT_EXISTS = new ErrorCode(1_004_000_005, "交通事故记录不存在");

    ErrorCode TRAFFIC_VIOLATION_NOT_EXISTS = new ErrorCode(1_004_000_006, "交通违法记录不存在");

}