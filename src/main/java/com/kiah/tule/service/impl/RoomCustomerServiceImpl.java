package com.kiah.tule.service.impl;

import com.kiah.tule.mapper.TRoomCustomerMapper;
import com.kiah.tule.mapper.TRoomInfoMapper;
import com.kiah.tule.mapper.TRoomRecordMapper;
import com.kiah.tule.model.TRoomCustomer;
import com.kiah.tule.model.TRoomInfo;
import com.kiah.tule.service.RoomCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RoomCustomerServiceImpl implements RoomCustomerService {
    @Autowired
    private TRoomInfoMapper tRoomInfoMapper;
    @Autowired
    private TRoomCustomerMapper tRoomCustomerMapper;
    @Autowired
    private TRoomRecordMapper tRoomRecordMapper;

    @Override
    public int addCustomer(TRoomCustomer roomCustomer) {
        int i = tRoomCustomerMapper.insert(roomCustomer);
        return i;
    }

    @Override
    public List<TRoomCustomer> selectCustomerByJlid(int jlid) {
        Example example =new Example(TRoomInfo.class);
        example.createCriteria().andCondition("jlid = '"+jlid+"'");
        List list= tRoomCustomerMapper.selectByExample(example);
        return list;
    }
}
