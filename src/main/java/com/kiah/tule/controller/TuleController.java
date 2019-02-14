package com.kiah.tule.controller;

import com.kiah.tule.model.AjaxJson;
import com.kiah.tule.service.RoomService;
import com.kiah.tule.utils.PropertiesUtils;
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
@RequestMapping("/tule")
public class TuleController {
    @Resource
    private RoomService roomService;
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name","kiah");
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("tjxx",roomService.tjFjxx());
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/loginByName",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson loginByName(HttpServletRequest request, String userName, String password){
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg="登录成功";
        String localUserName = PropertiesUtils.readKeyValue("config/tule.properties","userName");
        String localPassWord = PropertiesUtils.readKeyValue("config/tule.properties","passWord");
        if(StringUtils.equals(localUserName,userName)&&StringUtils.equals(localPassWord,password)){
            request.getSession().setAttribute("isLogin",true);
        }else{
            code = "1";
            msg= "登录失败，用户名或密码错误！";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }

    @RequestMapping(value = "/tjFjxx",method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson tjFjxx(HttpServletRequest request, String userName, String password){
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg="查询房间信息成功";

        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }

}
