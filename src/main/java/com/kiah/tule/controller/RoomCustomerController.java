package com.kiah.tule.controller;


import com.kiah.tule.model.AjaxJson;
import com.kiah.tule.model.TRoomCustomer;
import com.kiah.tule.service.RoomCustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/roomCustomer")
public class RoomCustomerController {
    @Resource
   private RoomCustomerService roomCustomerService;
    @RequestMapping(value = "/addCustomer",method = RequestMethod.POST)
    public AjaxJson addCustomer(TRoomCustomer roomCustomer){
        AjaxJson ajaxJson = new AjaxJson();
        String code ="0";
        String msg;
        try {
            int i = roomCustomerService.addCustomer(roomCustomer);
            msg="成功添加"+i+"条记录";
        } catch (Exception e) {
            e.printStackTrace();
            code="1";
            msg="添加失败,请联系管理员";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }
    @RequestMapping(value = "/selectCustomerByJlid",method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson selectCustomerByJlid(int jlid){
        AjaxJson ajaxJson = new AjaxJson();
        String code="0";
        String msg;
        List<TRoomCustomer> roomCustomers = null;
        try {
            roomCustomers = roomCustomerService.selectCustomerByJlid(jlid);
            msg="查到"+roomCustomers.size()+"条数据";
        } catch (Exception e) {
            e.printStackTrace();
            code="1";
            msg="查询失败,请联系管理员";
        }
        ajaxJson.setMsg(msg);
        ajaxJson.setCode(code);
        ajaxJson.setResult(roomCustomers);
        return ajaxJson;
    }
}
