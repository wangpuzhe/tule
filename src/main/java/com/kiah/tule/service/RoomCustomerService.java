package com.kiah.tule.service;

import com.kiah.tule.model.TRoomCustomer;

import java.util.List;

public interface RoomCustomerService {

    int addCustomer(TRoomCustomer roomCustomer);

    List<TRoomCustomer> selectCustomerByJlid(int jlid);
}
