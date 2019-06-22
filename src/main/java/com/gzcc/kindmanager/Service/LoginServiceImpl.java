package com.gzcc.kindmanager.Service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.gzcc.kindmanager.Repository.PersonalBillRepository;
import com.gzcc.kindmanager.Repository.UserRepository;
import com.gzcc.kindmanager.Util.AesCbcUtil;
import com.gzcc.kindmanager.Util.HttpRequestUtil;
import com.gzcc.kindmanager.Util.JwtUtil;
import com.gzcc.kindmanager.Util.ResourceBundleUtil;
import com.gzcc.kindmanager.domain.PersonalBill;
import com.gzcc.kindmanager.domain.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
@Autor zhuoyj[hopnetworks]
@Date 2018/8/11
@function 该类方法较多，为登录，注册，查询信息添加具体实现
@Description 实现类，继承登录接口

*/
@Service("loginService")
public class LoginServiceImpl implements LoginService {

private PersonalBillRepository personalBillRepository;
@Autowired
private UserRepository userRepository ;
    @Reference(version = "1.0.0")
 KindService kindService;
    //
//    @Autowired
//    private MachineRepository machineRepository ;

    private Logger logger = LoggerFactory.getLogger(getClass());
public final static String QQ_MAP_API=ResourceBundleUtil.getSystemString("QQ_MAP_API");
    @Autowired
public  LoginServiceImpl (PersonalBillRepository personalBillRepository){
this.personalBillRepository=personalBillRepository;
}
public LoginServiceImpl (){}
    @Override
    public Map login(String openId) {

//获取接收的密码和登录名
          Map map=new HashMap() ; //返回前端的键值对
        map .put("status",false);
        User user =userRepository.findByOpenId(openId );
        //数据库查找有没有user数据
        if (user != null) {
            // System.out.print(DigestUtils.md5Hex(user.getPassword()));

                //md5Hex(password)
                user .setLastTimeLogin(new Date() );

//                Logs logs =new Logs();
//                logs.setUser(username);
//                logs.setLocation(longitude+","+latitude);
//                logs .setLogintime(new Date() ) ;

                map.put("status",true);
                map.put("userId",  user.getOpenId() ) ;
               map.put("username",user.getUserName() );
//                map.put("company_id",user.getCompany().getId());
//                map.put("type",user.getType());
             //   String params = "location=" + latitude + "," +longitude + "&key=" + QQ_MAP_API +"&get_poi=0";
                // sending request
            //    String result = HttpRequestUtil.sendGet("https://apis.map.qq.com/ws/geocoder/v1/", params);

//                JSONObject json=new JSONObject();
//                String address="";
//                try {
//                  json = JSON.parseObject(result);
//                  address=json.getString("result");
//                    json=JSON.parseObject(address);
//                    if (address != null) {
//                        address = json.getString("address");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//             //   if (address != null) {
////                   logs.setAddress(address);
//
//             //   }
//logger.info("用户登录地址"+address);
//                map.put("address",address);
              //  logsRepository .save(logs);
                userRepository .save(user );
                String token=JwtUtil.getToken(openId,"normalUser");
//

                map.put("token",token );
                return map;
//                       {"MsgType":"login","User":"nnnnn","Status":"true/false"}
                //登陆成功


        } else {
            map.put("Msg","用户名错误");
            return map;
            //用户名错误
        }
    }
    @Override
    public Map regist(String iv, String encryptedData, String code) {
/**
 *
 * 功能描述:
 *
 * @param: [jsonObject]
 * @return: java.lang.String
 * @auther: hopnetworks
 * @function: 注册操作函数
 */
User user;
        Map map = new HashMap();
        map.put("status", false);
        // login code can not be null
        if (code == null || code.length() == 0) {

            map.put("msg", "请先登录微信");
            return map;
        }
        // mini-Program's AppID
        String wechatAppId =ResourceBundleUtil.getSystemString("AppId");
//String wechatId=System .getProperty("AppId");
        String wechatSecretKey=ResourceBundleUtil.getSystemString("AppKey");
        // mini-Program's session-key
//        String wechatSecretKey = "d49895f4f1904e3bee14cd7e8f350b66";

        String grantType = "authorization_code";

        // using login code to get sessionId and openId
        String params = "appid=" + wechatAppId + "&secret=" + wechatSecretKey + "&js_code=" + code + "&grant_type=" + grantType;

        // sending request
        String sr = HttpRequestUtil.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);

        // analysis request content
        JSONObject json = JSONObject.parseObject( sr);

        // getting session_key
        String sessionKey = json.get("session_key").toString();

        // getting open_id
        String openId = json.get("openid").toString();

        // decoding encrypted info with AES
        try {
            String result = AesCbcUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
            if (null != result && result.length() > 0) {

                map.put("status", true);
                map.put("Msg", "解密成功");
                JSONObject userInfoJSON = JSONObject.parseObject(result);
                System .out .println(result );
//                Map userInfo = new HashMap();
//
//                userInfo.put("openId", userInfoJSON.get("openId"));
//                userInfo.put("nickName", userInfoJSON.get("nickName"));
//                userInfo.put("gender", userInfoJSON.get("gender"));
//                userInfo.put("city", userInfoJSON.get("city"));
//                userInfo.put("province", userInfoJSON.get("province"));
//                userInfo.put("country", userInfoJSON.get("country"));
//                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
//                userInfo.put("unionId", userInfoJSON.get("unionId"));
           //    userInfo.put("telphone", userInfoJSON.get("telphone"));

                //System.out .println("ddddd是"+userInfoJSON.getString("openId") );
                try {
             user =userRepository.findByOpenId(openId );
                    if(user!=null){
                 return map;

                    }
                    user=new User();
                    user.setUserName(userInfoJSON.get("nickName").toString());
                    user.setOpenId(openId );
                    PersonalBill personalBill=new PersonalBill("默认账单"," ");
                    ObjectId objectId=personalBillRepository.save(personalBill).getPersonalBillId();
                    user.getPersonalBillId().add(objectId);
                    user.setDefalutpersonalBillId(objectId);
                    user.setPhone(userInfoJSON.get("telphone").toString());
                    userRepository.save(user);
                    map.put("userId",openId );
                }
                catch (Exception e){

                    map.put("repeatedAuth", "true");
                    map.put("msg", "解密失败,请重新授权,找不到此账号");
                    return map;

                }
        //        map.put("userInfo", userInfo);


                return map;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("msg", "解密失败,请重新授权");
        return map;
    }


//    @Override
//    public User searchInfo(String userName) throws ParseException {
////Map map =new HashMap() ;
//        //map .put("status",false) ;
//        boolean status = false;//查询是否成功的布尔变量
//        User user = null;
//        user = userRepository.findByUser(userName );//数据库寻找user并赋值
//        if (user != null) {
//            status = true;
//            return user;
//        } else {
//            return null;
//        }
//
//    }

    }

