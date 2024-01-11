package cn.iocoder.yudao.module.operation.dal.dataobject.insurance;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 保单 DO
 *
 * @author 芋道源码
 */
@TableName(value = "operation_insurance", autoResultMap = true)
@KeySequence("operation_insurance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 1 - 交强险，2 - 商业险,3-承运人责任险
     */
    private Integer insuranceType;
    /**
     * 车辆Id
     */
    private Long vehicleId;
    /**
     * 保单号
     */
    private String insuranceNumber;
    /**
     * 保险公司
     */
    private String insuranceCompany;
    /**
     * 总保费
     */
    private Double insuranceFee;
    /**
     * 生效日期
     */
    private LocalDate startDate;
    /**
     * 到期日期/到期状态判断
     */
    private LocalDate endDate;
    /**
     * 保单照片
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> pictures;

}