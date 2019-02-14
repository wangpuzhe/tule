package com.kiah.tule.controller;

import com.github.pagehelper.PageInfo;
import com.kiah.tule.model.AjaxJson;
import com.kiah.tule.model.DataTableResult;
import com.kiah.tule.model.TRoomInfo;
import com.kiah.tule.service.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author kiah
 * @version Version 1.0
 * @date 2018/11/10
 */
@Controller
@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomService roomServiceImpl;

    @RequestMapping("/manage")
    public String manage(){
        return "/room/manage";
    }

    @RequestMapping("/free")
    public String free(){
        return "room/free";
    }

    @RequestMapping("/yuding")
    public String yuding(Model model,String type){
        if(StringUtils.isNotEmpty(type)){
            model.addAttribute("type",type);
        }
        return "room/yuding";
    }

    @RequestMapping("/ruzhu")
    public String ruzhu(Model model,String type){
        if(StringUtils.isNotEmpty(type)){
            model.addAttribute("type",type);
        }
        return "room/ruzhu";
    }

    @RequestMapping("/daidasao")
    public String daidasao(){
        return "room/daidasao";
    }
    @RequestMapping("/ydList")
    public String ydList(){
        return "roomReservation/ydlist";
    }


    @RequestMapping(value = "/listRoom",method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult listRoom(String pageNum, String pageSize, String queryJson , HttpServletRequest request){
        try {
            PageInfo pageInfo = roomServiceImpl.listRoom(pageNum,pageSize,queryJson);
            return new DataTableResult(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加房间信息
     * @param tRoomInfo 房间信息
     * @return
     */
    @RequestMapping(value = "/addRoom",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson addRoom(TRoomInfo tRoomInfo){
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg ;
        try {
            int i = roomServiceImpl.addRoom(tRoomInfo);
            msg = "新增成功，共新增"+i+"条数据。";
        }catch (Exception e){
            e.printStackTrace();
            code = "1";
            msg= "新增失败，请联系管理员！";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }

    /**
     * 修改房间信息
     * @param tRoomInfo 房间信息
     * @return
     */
    @RequestMapping(value = "/editRoom",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson editRoom(TRoomInfo tRoomInfo){
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg = "修改成功";
        try {
            int i = roomServiceImpl.editRoom(tRoomInfo);
            msg = "修改成功，共修改"+i+"条数据。";
        }catch (Exception e){
            e.printStackTrace();
            code = "1";
            msg= "修改失败，请联系管理员！";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }

    /**
     * 删除房间信息
     * @param roomId 房间ID
     * @return
     */
    @RequestMapping(value = "/delRoom",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson delRoom(int roomId){
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg;
        try {
            int i = roomServiceImpl.delRoom(roomId);
            msg = "删除成功，共删除"+i+"条数据。";
        }catch (Exception e){
            e.printStackTrace();
            code = "1";
            msg= "删除失败，请联系管理员！";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }
    //listYdRoom
    @RequestMapping(value = "/listYdRoom",method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult listYdRoom(String pageNum, String pageSize,String queryJson){
        try {
            PageInfo pageInfo = roomServiceImpl.listYdRoom(pageNum,pageSize,queryJson);
            return new DataTableResult(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //listRzRoom
    @RequestMapping(value = "/listRzRoom",method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult listRzRoom(String pageNum, String pageSize,String queryJson){
        try {
            PageInfo pageInfo = roomServiceImpl.listRzRoom(pageNum,pageSize,queryJson);
            return new DataTableResult(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询空闲房子
     * @param queryJson
     * @return
     */
    @RequestMapping(value = "/kfListRoom",method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult kfListRoom(String pageNum, String pageSize,String queryJson){
        try {
            PageInfo pageInfo = roomServiceImpl.kfListRoom(pageNum,pageSize,queryJson);
            return new DataTableResult(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查找房间状态
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkZt",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson checkZt(String id){
        AjaxJson ajaxJson = new AjaxJson();
        String code= "0";
        String msg ;
        try {
            String zt = roomServiceImpl.checkZt(id);
            msg= "成功";
            ajaxJson.setResult(zt);
        } catch (Exception e) {
            e.printStackTrace();
            code="1";
            msg="失败";
        }
        ajaxJson.setMsg(msg);
        ajaxJson.setCode(code);
        return ajaxJson;
    }

}
