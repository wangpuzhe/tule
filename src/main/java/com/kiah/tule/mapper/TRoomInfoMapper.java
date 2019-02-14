package com.kiah.tule.mapper;

import com.kiah.tule.basemapper.BaseMapper;
import com.kiah.tule.model.TRoomInfo;
import com.kiah.tule.vo.VoRoomRz;
import com.kiah.tule.vo.VoRoomYd;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TRoomInfoMapper extends BaseMapper<TRoomInfo> {
    List<VoRoomYd> listYdRoom(Map<String, String> map);
    List<VoRoomRz> listRzRoom(Map<String, String> map);
    List<Map<String,String>> tjFjxx(@Param("startTime")String startTime,@Param("endTime")String endTime);
    List ydData(Map<String, String> map);
    List kfListRoom(Map<String,String> map);
}