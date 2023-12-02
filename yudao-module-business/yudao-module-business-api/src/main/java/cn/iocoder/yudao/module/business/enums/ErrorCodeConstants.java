package cn.iocoder.yudao.module.business.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * business 错误码枚举类
 * <p>
 * business 系统，使用 1-003-000-000 段
 */
public interface ErrorCodeConstants {
    ErrorCode DEVICE_NOT_EXISTS = new ErrorCode(1_003_000_001, "设备不存在");
    ErrorCode DRIVER_NOT_EXISTS = new ErrorCode(1_003_000_002, "司机不存在");
    ErrorCode VEHICLE_NOT_EXISTS = new ErrorCode(1_003_000_003, "车辆不存在");
}