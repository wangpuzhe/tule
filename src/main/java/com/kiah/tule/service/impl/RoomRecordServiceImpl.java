package com.kiah.tule.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kiah.tule.mapper.TRoomCustomerMapper;
import com.kiah.tule.mapper.TRoomInfoMapper;
import com.kiah.tule.mapper.TRoomRecordMapper;
import com.kiah.tule.mapper.TRoomReservationMapper;
import com.kiah.tule.model.*;
import com.kiah.tule.service.RoomRecordService;
import com.kiah.tule.service.RoomReservationService;
import com.kiah.tule.service.RoomService;
import com.kiah.tule.vo.VoRoomRz;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomRecordServiceImpl implements RoomRecordService {
    @Autowired
    private TRoomInfoMapper tRoomInfoMapper;
    @Autowired
    private TRoomCustomerMapper tRoomCustomerMapper;
    @Autowired
    private TRoomRecordMapper tRoomRecordMapper;
    @Autowired
    private TRoomReservationMapper tRoomReservationMapper;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomReservationService roomReservationService;

    private Log log = LogFactory.getLog(RoomRecordServiceImpl.class);

    @Override
    public PageInfo listRoomRecord(String pageNum, String pageSize, String queryJson) {
        if(!StringUtils.isEmpty(pageNum)&&!StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        }
        List list = tRoomRecordMapper.selectAll();
        return new PageInfo(list);
    }

    @Override
    public PageInfo rzjlData(String pageNum, String pageSize, String queryJson) {
        if(!StringUtils.isEmpty(pageNum)&&!StringUtils.isEmpty(pageSize)){
            PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        }
        JSONObject jsonObject = JSONObject.parseObject(queryJson);
        Example example = new Example(TRoomRecord.class);
        Example.Criteria criteria = example.createCriteria();
        StringBuffer condition = new StringBuffer(" 1=1 ");
        if(null!=jsonObject.getString("kssj")&& StringUtils.isNotEmpty(jsonObject.getString("kssj"))){
            condition.append(" and cjsj >= '"+jsonObject.getString("kssj")+"'");
        }
        if(null!=jsonObject.getString("jssj")&& StringUtils.isNotEmpty(jsonObject.getString("jssj"))){
            condition.append(" and cjsj <= '"+jsonObject.getString("jssj")+"'");
        }
        if(null!=jsonObject.getString("jbr")&& StringUtils.isNotEmpty(jsonObject.getString("jbr"))){
           // condition.append(" and cjr like  '%"+jsonObject.getString("jbr")+"%'");
            condition.append(" and ( cjr like  '%"+jsonObject.getString("jbr")+"%' or no like  '%"+jsonObject.getString("jbr")+"%' )");
        }
        if(null!=jsonObject.getString("sfjh")&& StringUtils.isNotEmpty(jsonObject.getString("sfjh"))){
            condition.append(" and sfjh = '"+jsonObject.getString("sfjh")+"'");
        }
        if(null!=jsonObject.getString("ghzt")&& StringUtils.isNotEmpty(jsonObject.getString("ghzt"))){
            condition.append(" and ghzt = '"+jsonObject.getString("ghzt")+"'");
        }
        criteria.andCondition(condition.toString());
        List list = tRoomRecordMapper.selectByExample(example);
        return new PageInfo(list);
    }

    @Override
    public int addRoomRecord(TRoomRecord roomRecord) {
        roomRecord.setCjsj(new Date());
        roomRecord.setSfjh("0");
        int i =tRoomRecordMapper.insertSelective(roomRecord);
        return i;
    }

    @Override
    public int editRoomRecord(TRoomRecord roomRecord) {
        int i = tRoomRecordMapper.updateByPrimaryKeySelective(roomRecord);
        return i;
    }

    @Override
    public int delRecord(int jlid) {
        int i = tRoomRecordMapper.deleteByPrimaryKey(jlid);
        return i;
    }

    @Override
    public List<VoRoomRz> selectOneVoRoomRz(String queryJson) {
        Map<String,String> map = new HashMap<>();
        JSONObject jsonObject =  JSON.parseObject(queryJson);
        String fjzt = (String) jsonObject.get("fjzt");
        String jlid = (String) jsonObject.get("jlid");
        map.put("fjzt",fjzt);
        map.put("jlid",jlid);
        List<VoRoomRz> voRoomRzs = tRoomRecordMapper.selectOneVoRoomRz(map);
        return voRoomRzs;
    }

    @Override
    public List<TRoomCustomer> selectRoomCustomer(String queryJson) {
        JSONObject jsonObject =  JSON.parseObject(queryJson);
        String jlid = (String) jsonObject.get("jlid");
        Example example = new Example(TRoomCustomer.class);
        example.createCriteria().andEqualTo("jlid",jlid);
        List<TRoomCustomer> list = tRoomCustomerMapper.selectByExample(example);
        return list;
    }

    @Override
    public int updateRecord(TRoomRecord roomRecord) {
        BigDecimal yj = roomRecord.getYj();
        BigDecimal styj = roomRecord.getStyj();
        BigDecimal je = roomRecord.getJe();
        BigDecimal result = je.add(yj.subtract(styj));
        roomRecord.setTfsj(new Date());
        roomRecord.setGxsj(new Date());
        roomRecord.setJe(result);
        roomRecord.setSfjh("1");
        int i = tRoomRecordMapper.updateByPrimaryKeySelective(roomRecord);
        return i;
    }

    @Override
    public void ruzhu(String roomCustomers, String roomId, String reservationId,String recordBz,String recordYj) throws Exception{
        //添加入住记录信息
        if(StringUtils.isNotEmpty(reservationId)){
            //更改预定记录"sfjh"为1
            tRoomReservationMapper.updateById(reservationId);
            TRoomReservation roomReservation = roomReservationService.findByID(reservationId);
            TRoomRecord roomRecord = new TRoomRecord();
            roomRecord.setNo(String.valueOf(roomReservation.getNo()));
            roomRecord.setRzts(roomReservation.getRzts());
            roomRecord.setRzsj(roomReservation.getRzsj());
            roomRecord.setTfsj(roomReservation.getTfsj());
            roomRecord.setDfr(roomReservation.getDfr());
            roomRecord.setQd(roomReservation.getQd());
            roomRecord.setYdid(Integer.valueOf(reservationId));
            roomRecord.setBz(recordBz);
            if(StringUtils.isNumeric(recordYj)){
                roomRecord.setYj(new BigDecimal(recordYj));
            }else {
                roomRecord.setYj(new BigDecimal("0"));
            }
            roomRecord.setJe(roomReservation.getJe());
            roomRecord.setCjr(roomReservation.getJbr());
            roomRecord.setCjsj(new Date());
            roomRecord.setSfjh("0");
            roomRecord.setGhzt("否");
            tRoomRecordMapper.insert(roomRecord);
            Integer recordId= roomRecord.getId();
            //添加入住人员信息
            JSONArray kerenJsonArray = JSONArray.parseArray(roomCustomers);
            for(int i=0;i<kerenJsonArray.size();i++){
                TRoomCustomer tRoomCustomer = JSONObject.parseObject(kerenJsonArray.getJSONObject(i).toJSONString(),TRoomCustomer.class);
                tRoomCustomer.setCjsj(new Date());
                tRoomCustomer.setJlid(recordId);
                tRoomCustomerMapper.insertSelective(tRoomCustomer);
            }
            //修改房间状态
            TRoomInfo tRoomInfo = new TRoomInfo();
            tRoomInfo.setId(Integer.valueOf(roomId));
            tRoomInfo.setZt("入住");
            roomService.editRoom(tRoomInfo);
        }
    }

    @Override
    public void guihuan(String ids,String zt) {
        String[] idArray = ids.split(",");
        for(String id:idArray){
            TRoomRecord tRoomRecord = new TRoomRecord();
            tRoomRecord.setId(Integer.valueOf(id));
            tRoomRecord.setGhzt(zt);
            tRoomRecordMapper.updateByPrimaryKeySelective(tRoomRecord);
        }
    }

    @Override
    public AjaxJson yjrz(TRoomReservation tRoomReservation,String roomCustomers, String roomId, String recordBz, String recordYj) throws Exception{
        //预定
        String reservationId;
        AjaxJson ajaxJson =roomReservationService.addRoomReservation(tRoomReservation);
        if(StringUtils.isNotEmpty(ajaxJson.getCode())&&StringUtils.equals("0",ajaxJson.getCode())){
            Map map = (HashMap)ajaxJson.getResult();
            reservationId = map.get("id").toString();
            //入住
            this.ruzhu(roomCustomers,roomId,reservationId,recordBz,recordYj);
            ajaxJson.setMsg("一键入住成功！");
        }
        return  ajaxJson;
    }

    @Override
    public AjaxJson xuzhu(String id, String xzsj, String xzff, String xzts,String xzqd,String xzjbr,String xzbz) {
        AjaxJson ajaxJson = new AjaxJson();
        String code = "0";
        String msg = "续住成功";
        try {
            TRoomRecord tRoomRecord = tRoomRecordMapper.selectByPrimaryKey(Integer.valueOf(id));
            String fjno = tRoomRecord.getNo();
            Date tfsj = tRoomRecord.getTfsj();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String tfsjStr = sdf.format(tfsj);
            Date xzsjDate  = sdf.parse(xzsj);
            //检查续住时间是否小于原来的时间
            if(tfsj.getTime()-xzsjDate.getTime()>0){
                ajaxJson.setCode("1");
                ajaxJson.setMsg("续住时间不能小于原退房时间！");
                return ajaxJson;
            }
            //检查该时间该房有没有预定 是否可以续住
            Example example = new Example(TRoomReservation.class);
            example.createCriteria().andEqualTo("no",fjno).andEqualTo("sfjh","0").andBetween("rzsj",tfsjStr,xzsj);
            int count = tRoomReservationMapper.selectCountByExample(example);
            if(count>0){
                ajaxJson.setCode("1");
                ajaxJson.setMsg("房间:"+fjno+"   在"+tfsjStr+"到"+xzsj+"时间段内，已经预定！");
                return ajaxJson;
            }
            //添加续住预定信息 返回新预定id
            Map<String,String> mapYd = new HashMap<>();
            mapYd.put("id",null);
            mapYd.put("xzsj",xzsj);
            mapYd.put("xzff",xzff);
            mapYd.put("xzts",xzts);
            mapYd.put("oldId",id);
            mapYd.put("xzqd",xzqd);
            mapYd.put("xzjbr",xzjbr);
            mapYd.put("xzbz",xzbz);
            tRoomReservationMapper.insertXzYdjl(mapYd);
            String newYdid = String.valueOf(mapYd.get("id"));
            //更新续住信息 (直接退房)
             /* 更新续住信息 退房时间 金额 入住天数--old
            Integer rztsOld = tRoomRecord.getRzts();
            BigDecimal jeOld = tRoomRecord.getJe();
            tRoomRecord.setTfsj(xzsjDate);
            tRoomRecord.setRzts(rztsOld+Integer.valueOf(xzts));
            tRoomRecord.setJe(jeOld.add(new BigDecimal(xzff)));*/
            tRoomRecord.setSfjh("1");
            tRoomRecord.setTfsj(new Date());
            tRoomRecord.setGxsj(new Date());
            tRoomRecord.setStyj(tRoomRecord.getYj());
            tRoomRecordMapper.updateByPrimaryKeySelective(tRoomRecord);
            //添加新的入住记录
            Map<String,String> mapRz = new HashMap<>();
            mapRz.put("id",null);
            mapRz.put("oldId",id);
            mapRz.put("ydId",newYdid);
            tRoomRecordMapper.insertXzRzjl(mapRz);
            String newRzId = String.valueOf(mapRz.get("id"));
            //给新的入住记录插入 客人信息
            Map<String ,String> mapLk = new HashMap<>();
            mapLk.put("oldId",id);
            mapLk.put("id",newRzId);
            tRoomCustomerMapper.insertXzLkjl(mapLk);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e,e.getCause());
            code = "1";
            msg = "系统出错,请联系管理员！";
        }
        ajaxJson.setCode(code);
        ajaxJson.setMsg(msg);
        return ajaxJson;
    }

    @Override
    public List<TRoomCustomer> findLkxx(String name) {
        PageHelper.startPage(Integer.valueOf(0),Integer.valueOf(5));
        Example example = new Example(TRoomCustomer.class);
        example.createCriteria().andLike("xm","%"+name+"%");
        List<TRoomCustomer> tRoomCustomerList = tRoomCustomerMapper.selectByExample(example);
        return tRoomCustomerList;
    }
}
