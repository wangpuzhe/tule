package com.kiah.tule.mapper;

import com.kiah.tule.basemapper.BaseMapper;
import com.kiah.tule.model.TRoomRecord;
import com.kiah.tule.vo.VoRoomRz;

import java.util.List;
import java.util.Map;

public interface TRoomRecordMapper extends BaseMapper<TRoomRecord> {
    List<VoRoomRz> selectOneVoRoomRz(Map<String,String> map);

    void insertXzRzjl(Map<String,String> map2);
}