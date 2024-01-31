package cn.iocoder.yudao.module.gps.dal.mysql.fence;

import cn.iocoder.yudao.module.gps.dal.dataobject.fence.FenceDO;

import java.util.List;
import java.util.Map;


public interface FenceMapper {

	List<FenceDO> queryFenceList(Map<String, Object> param);
	
	List<FenceDO> queryFenceLimit(Map<String, Object> param);
	
	void insertFenceAndGetId(FenceDO fence);
	
	void updateFence(FenceDO fence);
	
	void insertFenceAndDept(Map<String, Object> param);
	
	void deleteFenceAndDept(Long fenceId);
	
	void insertFenceExcludeVehicle(Map<String, Object> param);
	
	void deleteFenceExcludeVehicle(Long fenceId);

	FenceDO queryFenceDetail(Long fenceId);
	
	void deleteFence(Long fenceId);
	
	List<Map<String, Object>> queryFenceDepts(Long fenceId);
	
	List<Map<String, Object>> queryFenceExcludeVehicle(Long fenceId);
	
	List<Map<String, Object>> queryInsideFenceVehicle(Map<String, Object> param);
	
}
