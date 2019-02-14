package com.kiah.tule.service;

import com.github.pagehelper.PageInfo;
import com.kiah.tule.model.TRoomInfo;

import java.util.List;
import java.util.Map;

/**
 * @author kiah
 * @version Version 1.0
 * @date 2018/11/10
 */
public interface RoomService {
    PageInfo listRoom(String pageNum,String pageSize,String queryJson);
    int addRoom(TRoomInfo tRoomInfo);
    int editRoom(TRoomInfo tRoomInfo);
    int delRoom(int roomId);
   PageInfo listYdRoom(String pageNum, String pageSize,String queryJson);
   PageInfo listRzRoom(String pageNum, String pageSize,String queryJson);

    /**
     * 首页统计房间信息
     * @return
     */
   Map<String,String> tjFjxx();

    PageInfo kfListRoom(String pageNum,String pageSize,String queryJson);

    String checkZt(String id);
}
