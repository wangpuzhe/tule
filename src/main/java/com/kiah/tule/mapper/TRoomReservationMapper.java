package com.kiah.tule.mapper;

import com.kiah.tule.basemapper.BaseMapper;
import com.kiah.tule.model.TRoomReservation;
import java.util.Map;

public interface TRoomReservationMapper extends BaseMapper<TRoomReservation> {
    void updateById(String reservationId);
    int checkRoomIsUse(Map paraMap);

    void insertXzYdjl(Map<String,String> map);
}