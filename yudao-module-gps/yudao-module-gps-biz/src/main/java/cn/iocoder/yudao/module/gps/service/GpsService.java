package cn.iocoder.yudao.module.gps.service;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.business.api.driver.DriverApi;
import cn.iocoder.yudao.module.business.api.driver.dto.DriverRespDTO;
import cn.iocoder.yudao.module.business.api.vehicle.VehicleApi;
import cn.iocoder.yudao.module.business.api.vehicle.dto.VehicleRespDTO;
import cn.iocoder.yudao.module.gps.controller.admin.position.vo.VehiclePositionVO;
import cn.iocoder.yudao.module.gps.dal.dataobject.position.VehiclePositionStatusDO;
import cn.iocoder.yudao.module.gps.dal.mysql.position.VehiclePositionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GpsService {

    @Autowired
    private VehiclePositionMapper vehiclePositionMapper;
    @Resource
    private VehicleApi vehicleApi;
    @Resource
    private DriverApi driverApi;

    public List<VehiclePositionStatusDO> getList() {
        return vehiclePositionMapper.selectList();
    }

    /*public List<DataPackage> queryGpsList(Long vehicleId,Long startTime, Long endTime,Integer devtype){
        List<DataPackage> gpslist=new ArrayList<>();
        try {
            if(vehicleId!=null) {

                StoreService.Params param = new StoreService.Params();
                param.ns=namespace;
                param.vehicleId=String.valueOf(vehicleId);
                param.devtype=devtype;
                param.dataType = MessageType.MessageTypeReport.REPORT_GPS_DATA;
                param.timeFrom = startTime;
                param.timeTo = endTime;
                param.order = 1;
                gpslist.addAll(storeService.load(param));
            }
        } catch (Exception e) {
            log.warn("{}-{}-{}-{}查询gps出错",vehicleId,startTime,endTime,devtype);
        }
        return gpslist;
    }*/
}
