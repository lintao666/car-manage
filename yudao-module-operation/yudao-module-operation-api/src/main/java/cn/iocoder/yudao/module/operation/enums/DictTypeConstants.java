package cn.iocoder.yudao.module.operation.enums;

/**
 * System 字典类型的枚举类
 *
 * @author 芋道源码
 */
public interface DictTypeConstants {
    /**
     * 审验类型 1车辆年审，2气瓶年审
     */
    String OPERATION_INSPECTION_TYPE = "inspection_type";

    /**
     * 交通事故-责任情况
     */
    String OPERATION_RESPONSIBILITY = "responsibility";
    /**
     * 交通违法-信息来源
     */
    String OPERATION_SOURCE = "source";
    /**
     * 交通违法-处理状态 1已处理 0未处理
     */
    String OPERATION_HANDLE_STATUS = "handle_status";
    /**
     * 交通违法-决定书类别：1-简易程序  2-一般程序
     */
    String DECISION_TYPE = "decision_type";
    /**
     * 车辆维修-损坏等级
     */
    String OPERATION_REPAIR_LEVEL = "repair_level";
}
