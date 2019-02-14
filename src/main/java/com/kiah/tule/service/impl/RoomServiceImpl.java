package com.kiah.tule.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kiah.tule.mapper.TRoomCustomerMapper;
import com.kiah.tule.mapper.TRoomInfoMapper;
import com.kiah.tule.mapper.TRoomRecordMapper;
import com.kiah.tule.model.TRoomInfo;
import com.kiah.tule.vo.VoRoomRz;
import com.kiah.tule.vo.VoRoomYd;
import com.kiah.tule.service.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kiah
 * @version Version 1.0
 * @date 2018/11/10
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private TRoomInfoMapper tRoomInfoMapper;
    @Autowired
    private TRoomCustomerMapper tRoomCustomerMapper;
    @Autowired
    private TRoomRecordMapper tRoomRecordMapper;
    @Override
    public PageInfo listRoom(String pageNum, String pageSize, String queryJson) {
        if(!StringUtils.isEmpty(pageNum)&&!StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        }
        JSONObject jsonObject= JSON.parseObject(queryJson);
        String zt = (String) jsonObject.get("zt");
        String keywords = (String) jsonObject.get("keywords");
        Example example =new Example(TRoomInfo.class);
       /* example.createCriteria().andCondition("1=1 and zt="+zt+" and ( fjh like '%"+keywords+"%' or fjm like '%"+keywords+"%')");*/
        example.createCriteria().andCondition("1=1 and zt like '%"+zt+"%' and ( no like '%"+keywords+"%' or mc like '%"+keywords+"%')");
        List list= tRoomInfoMapper.selectByExample(example);
       // List list = tRoomInfoMapper.selectAll();
        return new PageInfo(list);
    }

    @Override
    public int addRoom(TRoomInfo tRoomInfo) {
        tRoomInfo.setCjsj(new Date());
        int i = tRoomInfoMapper.insertSelective(tRoomInfo);
        return i;
    }

    @Override
    public int editRoom(TRoomInfo tRoomInfo) {
        tRoomInfo.setGxsj(new Date());
        int i = tRoomInfoMapper.updateByPrimaryKeySelective(tRoomInfo);
        return i;
    }

    @Override
    public int delRoom(int roomId) {
        int i = tRoomInfoMapper.deleteByPrimaryKey(roomId);
        return i;
    }

    @Override
    public PageInfo listYdRoom(String pageNum, String pageSize,String queryJson) {
        if(!StringUtils.isEmpty(pageNum)&&!StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        }
        JSONObject jsonObject= JSON.parseObject(queryJson);
        String zt = (String) jsonObject.get("zt");
        String lx = (String) jsonObject.get("cxlx");
        String sfjh = (String) jsonObject.get("sfjh");
        String keywords = (String) jsonObject.get("keywords");
        //example.createCriteria().andCondition("1=1 and zt like '%"+zt+"%' and ( no like '%"+keywords+"%' or mc like '%"+keywords+"%')");
        //List list= tRoomInfoMapper.selectByExample(example);
        // List list = tRoomInfoMapper.selectAll();
        Map<String,String> map = new HashMap<>();
        map.put("zt",zt);
        map.put("sfjh",sfjh);
        map.put("keywords","%"+keywords+"%");
        //List<VoRoomYd> list = null;
       List<VoRoomYd> list = tRoomInfoMapper.listYdRoom(map);
        return new PageInfo(list);
    }

    @Override
    public PageInfo listRzRoom(String pageNum, String pageSize,String queryJson) {
        if(!StringUtils.isEmpty(pageNum)&&!StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        }
        JSONObject jsonObject= JSON.parseObject(queryJson);
        String zt = (String) jsonObject.get("zt");
        String sfjh = (String) jsonObject.get("sfjh");
        String keywords = (String) jsonObject.get("keywords");
        String type = (String) jsonObject.get("type");
        Map<String,String> map = new HashMap<>();
        map.put("zt",zt);
        map.put("sfjh",sfjh);
        map.put("keywords","%"+keywords+"%");
        if(StringUtils.isNotEmpty(type)&&StringUtils.equals(type,"jrdtf")){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());
            String startTime = today+" 00:00:00";
            String endTime = today+" 23:59:59";
            map.put("startTime",startTime);
            map.put("endTime",endTime);
        }
        List<VoRoomRz> list = tRoomInfoMapper.listRzRoom(map);
        return new PageInfo(list);
    }

    @Override
    public PageInfo kfListRoom(String pageNum,String pageSize,String queryJson) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(today);
        String kssj = todayStr+" 00:00:00";
        String jssj = todayStr+" 23:59:59";
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String kssj=simpleDateFormat.format(today);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        String time = sdf.format(c.getTime());
        String jssj = time+" 12:30:59";*/
        JSONObject jsonObject = JSON.parseObject(queryJson);
        String zt = (String) jsonObject.get("zt");
        String keywords = (String) jsonObject.get("keywords");
        Map<String,String> map = new HashMap<>();
        map.put("zt",zt);
        map.put("mc",keywords);
        map.put("no",keywords);
        map.put("kssj",kssj);
        map.put("jssj",jssj);
        if(!StringUtils.isEmpty(pageNum)&&!StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        }
        List list = tRoomInfoMapper.kfListRoom(map);
        return new PageInfo(list);
    }

    @Override
    public Map<String, String> tjFjxx() {
        Map result = new HashMap();
        String jrkfs = "0";//今日空房数
        String jrdrzs = "0";//今日待入住数
        String jrdtfs = "0";//今日待退房数
        String yds = "0";//已预订数
        String rzs = "0";//已入住数
        String ddss = "0";//待打扫数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        String startTime = today+" 00:00:00";
        String endTime = today+" 23:59:59";
        List<Map<String,String>> list = tRoomInfoMapper.tjFjxx(startTime,endTime);
        for(Map<String,String> obj:list){
            String zt = obj.get("zt");
            String sl =String.valueOf(obj.get("sl"));
            if(StringUtils.equals("预定",zt)){
                yds=sl;
            }else if(StringUtils.equals("入住",zt)){
                rzs=sl;
            }else if(StringUtils.equals("待打扫",zt)){
                ddss=sl;
            }else if(StringUtils.equals("今日空房",zt)){
                jrkfs=sl;
            }else if(StringUtils.equals("今日待入住",zt)){
                jrdrzs=sl;
            }else if(StringUtils.equals("今日待退房",zt)){
                jrdtfs=sl;
            }
        }
        result.put("yds",yds);
        result.put("rzs",rzs);
        result.put("ddss",ddss);
        result.put("jrkfs",jrkfs);
        result.put("jrdrzs",jrdrzs);
        result.put("jrdtfs",jrdtfs);
        return result;
    }

    @Override
    public String checkZt(String id) {
        TRoomInfo info= tRoomInfoMapper.selectByPrimaryKey(Integer.valueOf(id));
        String zt;
        if(null!=info){
            return zt = info.getZt();
        }else{
            return zt = null;
        }
    }
}
