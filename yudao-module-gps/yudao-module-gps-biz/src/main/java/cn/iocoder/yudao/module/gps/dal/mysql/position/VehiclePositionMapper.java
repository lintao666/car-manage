package cn.iocoder.yudao.module.gps.dal.mysql.position;


import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.gps.dal.dataobject.position.VehiclePositionStatusDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehiclePositionMapper extends BaseMapperX<VehiclePositionStatusDO> {
}