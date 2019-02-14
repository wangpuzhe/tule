package com.kiah.tule.mapper;

import com.kiah.tule.basemapper.BaseMapper;
import com.kiah.tule.model.TRoomCustomer;

import java.util.Map;

public interface TRoomCustomerMapper extends BaseMapper<TRoomCustomer> {
    void insertXzLkjl(Map<String,String> mapLk);
}