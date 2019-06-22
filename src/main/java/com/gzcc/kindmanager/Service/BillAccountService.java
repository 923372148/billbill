package com.gzcc.kindmanager.Service;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.Map;

@Service
public interface BillAccountService {

    Map newAccount(String personalBillId,String kindId,String comment,long money,boolean ifIncome);
    Map newBill(String openId,String personalBillName ,String description);


}
