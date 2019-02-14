package com.kiah.tule.service;

import com.github.pagehelper.PageInfo;
import com.kiah.tule.model.AjaxJson;
import com.kiah.tule.model.TRoomCustomer;
import com.kiah.tule.model.TRoomRecord;
import com.kiah.tule.model.TRoomReservation;
import com.kiah.tule.vo.VoRoomRz;

import java.util.List;

public interface RoomRecordService {
    PageInfo listRoomRecord(String pageNum, String pageSize, String queryJson);
    PageInfo rzjlData(String pageNum, String pageSize, String queryJson);
    int addRoomRecord(TRoomRecord roomRecord);
    int editRoomRecord(TRoomRecord roomRecord);
    int delRecord(int jlid);
    List<VoRoomRz> selectOneVoRoomRz(String queryJson);

    List<TRoomCustomer> selectRoomCustomer(String queryJson);

    int updateRecord(TRoomRecord roomRecord);

    /**
     * 一键入住
     */
    AjaxJson yjrz(TRoomReservation tRoomReservation, String roomCustomers, String roomId, String recordBz, String recordYj) throws Exception;

    /**
     * 添加入住信息
     * @param roomCustomers  入住客人信息
     * @param roomId      房间ID
     * @param reservationId    预定ID
     * @return
     */
    void ruzhu(String roomCustomers, String roomId, String reservationId,String recordBz,String recordYj) throws Exception;

    /**
     * 根据id设置该记录归还状态
     * @param ids
     * @param zt 归还状态 是/否
     */
    void guihuan(String ids,String zt);

    /**
     * 续住
     * @param id    入住记录id
     * @param xzsj  续住时间
     * @param xzff  续住房费
     * @param xzts  续住天数
     * @return
     */
    AjaxJson xuzhu(String id,String xzsj,String xzff,String xzts,String xzqd,String xzjbr,String xzbz);

    /**
     * 根据名字查询旅客信息
     */
    List<TRoomCustomer> findLkxx(String name);
}
