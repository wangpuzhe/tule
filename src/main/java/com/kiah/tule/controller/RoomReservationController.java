package com.kiah.tule.controller;

import com.github.pagehelper.PageInfo;
import com.kiah.tule.mapper.TRoomReservationMapper;
import com.kiah.tule.model.AjaxJson;
import com.kiah.tule.model.DataTableResult;
import com.kiah.tule.model.TRoomReservation;
import com.kiah.tule.service.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/roomReservation")
public class RoomReservationController {
    @Autowired
    private RoomReservationService roomReservationService;

    /**
     * 可预订的房间
     * @param queryJson
     * @return
     */
    @RequestMapping(value = "/ydData",method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult ydData(String pageNum,String pageSize,String queryJson){
        try {
            PageInfo pageInfo= roomReservationService.ydData(pageNum,pageSize,queryJson);
            return new DataTableResult(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/addRoomReservation",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addRoomReservation(TRoomReservation tRoomReservation){
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg="";
        try {
            ajaxJson = roomReservationService.addRoomReservation(tRoomReservation);
        } catch (Exception e) {
            e.printStackTrace();
            msg="请联系管理员";
            code ="1";
            ajaxJson.setCode(code);
            ajaxJson.setMsg(msg);
        }
        return ajaxJson;
    }

    /**
     * 所有预定信息
     * @param queryJson
     * @return
     */
    @RequestMapping(value = "/ydlist",method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult ydlist(String pageNum,String pageSize,String queryJson){
        try {
            PageInfo pageInfo = roomReservationService.ydlist(pageNum,pageSize,queryJson);
            return new DataTableResult(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 删除预定记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/delReservation",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delReservation(String id){
        AjaxJson ajaxJson = new AjaxJson();
        String code;
        String msg;
        try {
            int i = roomReservationService.delReservation(id);
            code = "0";
            msg = "成功删除"+i+"条预定记录!";
        } catch (Exception e) {
            e.printStackTrace();
            code="1";
            msg = "请联系管理员!";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return  ajaxJson;
    }
}
