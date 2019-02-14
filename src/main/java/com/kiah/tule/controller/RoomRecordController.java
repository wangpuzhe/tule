package com.kiah.tule.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kiah.tule.model.*;
import com.kiah.tule.service.impl.RoomRecordServiceImpl;
import com.kiah.tule.vo.VoRoomRz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/roomRecord")
public class RoomRecordController {
    @Resource
    private RoomRecordServiceImpl roomRecordServiceImpl;

    @RequestMapping("/list")
    public String list(){
        return "/roomRecord/list";
    }

    @RequestMapping(value = "/listRoomRecord",method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult listRoomRecord(String start, String length, String queryJson , HttpServletRequest request){
        try {
            PageInfo pageInfo = roomRecordServiceImpl.listRoomRecord(start,length,queryJson);
            return new DataTableResult(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/rzjlData",method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult rzjlData(String pageNum, String pageSize, String queryJson , HttpServletRequest request){
        try {
            PageInfo pageInfo = roomRecordServiceImpl.rzjlData(pageNum,pageSize,queryJson);
            return new DataTableResult(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/addRoomRecord",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addRoomRecord(TRoomRecord roomRecord){
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg ;
        try {
            int i = roomRecordServiceImpl.addRoomRecord(roomRecord);
            msg = "新增成功，共新增"+i+"条数据。";
        } catch (Exception e) {
            e.printStackTrace();
            code ="1";
            msg= "新增失败，请联系管理员！";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }
    @RequestMapping(value = "/editRoomRecord",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson editRoomRecord(TRoomRecord roomRecord){
        roomRecord.setGxsj(new Date());
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg;
        try {
            int i = roomRecordServiceImpl.editRoomRecord(roomRecord);
            msg = "修改成功，共修改"+i+"条数据。";
        } catch (Exception e) {
            e.printStackTrace();
            code = "1";
            msg= "修改失败，请联系管理员！";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }

    @RequestMapping(value = "/ruzhu" ,method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson ruzhu(String roomCustomers,String roomId,String reservationId,String recordBz,String recordYj,HttpServletRequest request){
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg = "入住成功";
        try {
            roomRecordServiceImpl.ruzhu(roomCustomers,roomId,reservationId,recordBz,recordYj);
        }catch (Exception e){
            code = "1";
            msg = "入住失败,请联系管理员";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }

    @RequestMapping(value = "/yjrz" ,method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson yjrz(String ydxx, String roomCustomers,String roomId,String recordBz,String recordYj,HttpServletRequest request) throws Exception{
        TRoomReservation tRoomReservation = JSONObject.parseObject(ydxx.substring(1,ydxx.length()-1),TRoomReservation.class);
        AjaxJson ajaxJson =  roomRecordServiceImpl.yjrz(tRoomReservation,roomCustomers,roomId,recordBz,recordYj);
        return ajaxJson;
    }

    @RequestMapping(value = "/delRecord",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delRecord(int jlid){
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg;
        try {
            int i = roomRecordServiceImpl.delRecord(jlid);
            msg  = "删除"+i+"条记录";
        } catch (Exception e) {
            e.printStackTrace();
            code="1";
            msg = "删除失败";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }
    @RequestMapping(value = "/exitRoom",method = RequestMethod.POST)
    public ModelAndView exitRoom(String queryJson){
        ModelAndView modelAndView = new ModelAndView();
        List<VoRoomRz> voRoomRzs= roomRecordServiceImpl.selectOneVoRoomRz(queryJson);
        List<TRoomCustomer> roomCustomers = roomRecordServiceImpl.selectRoomCustomer(queryJson);
        return modelAndView;
    }

    /**
     * 退房并更新房间状态
     * @param roomRecord
     * @return
     */

    @RequestMapping(value = "/updateRecord",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson updateRecord(TRoomRecord roomRecord){
        AjaxJson ajaxJson = new AjaxJson();
        String code="0";
        String msg;
        try {
            int i = roomRecordServiceImpl.updateRecord(roomRecord);
            msg="退房成功!";
        } catch (Exception e) {
            e.printStackTrace();
            msg="请联系管理员!";
            code="1";
        }
        ajaxJson.setMsg(msg);
        ajaxJson.setCode(code);
        return ajaxJson;
    }

    @RequestMapping(value = "/guihuan",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson guihuan(String ids,String zt){
        AjaxJson ajaxJson = new AjaxJson();
        String code="0";
        String msg;
        try {
            roomRecordServiceImpl.guihuan(ids,zt);
            msg="归还成功!";
        } catch (Exception e) {
            e.printStackTrace();
            msg="请联系管理员!";
            code="1";
        }
        ajaxJson.setMsg(msg);
        ajaxJson.setCode(code);
        return ajaxJson;
    }

    @RequestMapping(value = "/xuzhu",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson xuzhu(String id,String xzsj,String xzff,String xzts,String xzqd,String xzjbr,String xzbz){
        return roomRecordServiceImpl.xuzhu(id,xzsj,xzff,xzts,xzqd,xzjbr,xzbz);
    }

    @RequestMapping(value = "/findLkxx",method = RequestMethod.POST)
    @ResponseBody
    public List<TRoomCustomer> findLkxx(String name){
        return roomRecordServiceImpl.findLkxx(name);
    }
}
