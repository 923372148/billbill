package com.gzcc.bill.Service;


import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Map;

/**
@Autor zhuoyj[hopnetworks]
@Date 2018/8/11
@function   将登录逻辑处理封装成一个接口
@Description 服务类

*/
@Service
public interface LoginService {
//
//


 //String searchInfomation(JSONObject json) throws ParseException;

 //String modifyPassword(JSONObject json);
// String findBalance(JSONObject json);

//返回键值对
  Map login(String openId);
    Map regist(String iv, String encryptedData, String code);




    //return "s";
//
//    };
//String regist();
//
}
