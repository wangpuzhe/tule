package com.kiah.tule.service;

import com.github.pagehelper.PageInfo;
import com.kiah.tule.model.AjaxJson;
import com.kiah.tule.model.TRoomReservation;

import java.util.Date;

public interface RoomReservationService {
    PageInfo ydData(String pageNum,String pageSize,String queryJson);

    AjaxJson addRoomReservation(TRoomReservation tRoomReservation);

    PageInfo ydlist(String pageNum,String pageSize,String queryJson);

    TRoomReservation findByID(String reservationId);

    int delReservation(String id);

    /**
     * 判断房间该时间段能否预定
     * @param fjid
     * @param rzsj
     * @param tfsj
     * @return
     */
    boolean checkRoomIsUse(Integer fjid, Date rzsj,Date tfsj);
}
