package cn.iocoder.yudao.module.gps.service.position;

import cn.iocoder.yudao.module.gps.dal.dataobject.position.VehiclePositionStatusDO;
import cn.iocoder.yudao.module.gps.dal.mysql.position.VehiclePositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    VehiclePositionMapper vehiclePositionMapper;

    public List<VehiclePositionStatusDO> getList() {
        return vehiclePositionMapper.selectList();
    }

}
