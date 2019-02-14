package com.kiah.tule.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kiah.tule.mapper.TRoomCustomerMapper;
import com.kiah.tule.mapper.TRoomInfoMapper;
import com.kiah.tule.mapper.TRoomRecordMapper;
import com.kiah.tule.mapper.TRoomReservationMapper;
import com.kiah.tule.model.AjaxJson;
import com.kiah.tule.model.TRoomInfo;
import com.kiah.tule.model.TRoomReservation;
import com.kiah.tule.service.RoomReservationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {
    @Autowired
    private TRoomInfoMapper tRoomInfoMapper;
    @Autowired
    private TRoomCustomerMapper tRoomCustomerMapper;
    @Autowired
    private TRoomRecordMapper tRoomRecordMapper;
    @Autowired
    private TRoomReservationMapper tRoomReservationMapper;
    @Override
    public PageInfo ydData(String pageNum,String pageSize,String queryJson) {
        if(!StringUtils.isEmpty(pageNum)&&!StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        }
        JSONObject jsonObject = JSON.parseObject(queryJson);
        String kssj = jsonObject.getString("kssj");
        String jssj = jsonObject.getString("jssj");
        String lx = jsonObject.getString("lx");
        String mc = jsonObject.getString("keywords");
        String no = jsonObject.getString("keywords");
        Map<String,String> map = new HashMap<>();
        map.put("kssj",kssj);
        map.put("jssj",jssj);
        map.put("lx",lx);
        map.put("mc",mc);
        map.put("no",no);
        List list = tRoomInfoMapper.ydData(map);
        return new PageInfo(list);
    }

    @Override
    public AjaxJson addRoomReservation(TRoomReservation tRoomReservation) {
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg="";
        if(null==tRoomReservation.getFjid()){
            code = "1";
            msg = "请选择房间！";
            ajaxJson.setCode(code);
            ajaxJson.setMsg(msg);
            return  ajaxJson;
        }
        if(null==tRoomReservation.getRzsj()){
            code = "1";
            msg = "请输入入住时间！";
            ajaxJson.setCode(code);
            ajaxJson.setMsg(msg);
            return  ajaxJson;
        }
        if(null==tRoomReservation.getTfsj()){
            code = "1";
            msg = "请输入退房时间！";
            ajaxJson.setCode(code);
            ajaxJson.setMsg(msg);
            return  ajaxJson;
        }
        Boolean flag = this.checkRoomIsUse(tRoomReservation.getFjid(),tRoomReservation.getRzsj(),tRoomReservation.getTfsj());
        if(flag){
            tRoomReservation.setCjsj(new Date());
            int i = tRoomReservationMapper.insert(tRoomReservation);
            code = "0";
            msg = "预定成功！";
            ajaxJson.setCode(code);
            ajaxJson.setMsg(msg);
            Map map = new HashMap();
            map.put("id",tRoomReservation.getId());
            ajaxJson.setResult(map);

        }else {
            code = "1";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String kssj = sdf.format(tRoomReservation.getRzsj());
            String jssj = sdf.format(tRoomReservation.getTfsj());
            msg = "房间号:"+tRoomReservation.getNo()+"在"+kssj+"到"+jssj+"时间段内已经预定，请重新选择时间范围！";
            ajaxJson.setCode(code);
            ajaxJson.setMsg(msg);
        }
        return ajaxJson;
    }

    @Override
    public PageInfo ydlist(String pageNum,String pageSize,String queryJson) {
        if(!StringUtils.isEmpty(pageNum)&&!StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        }
        JSONObject jsonObject= JSON.parseObject(queryJson);
        String sfjh = (String) jsonObject.get("sfjh");
        String keywords = (String) jsonObject.get("keywords");
        String type = (String) jsonObject.get("type");
        Example example = new Condition(TRoomReservation.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(sfjh)){
            criteria.andEqualTo("sfjh",sfjh);
        }
        if(StringUtils.isNotEmpty(keywords)){
            criteria .andCondition("(no like '%"+keywords+"%' or mc like '%"+keywords+"%')");
        }
        if(StringUtils.isNotEmpty(type)&&StringUtils.equals(type,"jrdrz")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());
            String startTime = today+" 00:00:00";
            String endTime = today+" 23:59:59";
            criteria.andBetween("rzsj",startTime,endTime);
        }
        example.setOrderByClause("rzsj");
        List list= tRoomReservationMapper.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public TRoomReservation findByID(String reservationId) {
        Example example = new Example(TRoomReservation.class);
        example.createCriteria().andEqualTo("id",reservationId);
        List list = tRoomReservationMapper.selectByExample(example);
        if (list.size()>0){
            TRoomReservation roomReservation =(TRoomReservation) list.get(0);
            return roomReservation;
        }else {
            return null;
        }
    }

    @Override
    public int delReservation(String id) {
        int i=0;
        if (StringUtils.isNotEmpty(id)) {
            i = tRoomReservationMapper.deleteByPrimaryKey(Integer.valueOf(id));
        }
        return i;
    }

    @Override
    public boolean checkRoomIsUse(Integer fjid, Date rzsj, Date tfsj) {
        boolean flag;
        try {
            Map paraMap = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String kssj = sdf.format(rzsj);
            String jssj = sdf.format(tfsj);
            paraMap.put("fjid",fjid);
            paraMap.put("kssj",kssj);
            paraMap.put("jssj",jssj);
            int count = tRoomReservationMapper.checkRoomIsUse(paraMap);
            if(count>0){
                flag = false;
            }else {
                flag = true;
            }
            return flag;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
