package com.gzcc.bill.Service;


import com.alibaba.fastjson.JSONObject;
import com.gzcc.bill.Repoistory.PersonalBillRepoistory;
import com.gzcc.bill.Repoistory.UserRepoistory;
import com.gzcc.bill.Util.AesCbcUtil;
import com.gzcc.bill.Util.HttpRequestUtil;
import com.gzcc.bill.Util.ResourceBundleUtil;
import com.gzcc.bill.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
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

private PersonalBillRepoistory personalBillRepoistory;
@Autowired
private UserRepoistory userRepoistory ;
    //
//    @Autowired
//    private MachineRepoistory machineRepoistory ;


    private Logger logger = LoggerFactory.getLogger(getClass());
public final static String QQ_MAP_API=ResourceBundleUtil.getSystemString("QQ_MAP_API");
    @Autowired
public  LoginServiceImpl (PersonalBillRepoistory personalBillRepoistory){

this.personalBillRepoistory=personalBillRepoistory;
}
    @Override
    public Map login(String username,String password,String longitude,String latitude) {

//获取接收的密码和登录名
          Map map=new HashMap() ; //返回前端的键值对
        map .put("status",false);
        User user = userRepoistory.findByAccountId("");
        //数据库查找有没有user数据
        if (user != null) {
            // System.out.print(DigestUtils.md5Hex(user.getPassword()));
            if (password.equals(user.getPassword())) {
                //md5Hex(password)
                user .setLasttime(new Date() );
                user.setStatus(true);
                Logs logs =new Logs();
                logs.setUser(username);
                logs.setLocation(longitude+","+latitude);
                logs .setLogintime(new Date() ) ;

                map.put("status",true);
                map.put("user_id",  user.getId() ) ;
                map.put("username",user.getUser() );
                map.put("company_id",user.getCompany().getId());
                map.put("type",user.getType());
                String params = "location=" + latitude + "," +longitude + "&key=" + QQ_MAP_API +"&get_poi=0";
                // sending request
                String result = HttpRequestUtil.sendGet("https://apis.map.qq.com/ws/geocoder/v1/", params);

                JSONObject json=new JSONObject();
                String address="";
                try {
                  json = GetRequestJsonUtils.getJson(result);
                  address=json.getString("result");
                    json=GetRequestJsonUtils.getJson(address);
                    if (address != null) {
                        address = json.getString("address");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (address != null) {
                   logs.setAddress(address);
                    user.setLastip(address);
                }
logger.info("用户登录地址"+address);
                map.put("address",address);
                logsRepoistory .save(logs);
                userRepoistory .save(user );
                String token;
                switch (user.getType()){
                    // 1234 为数据库相对应的类型，
                    case "1" :  token = JwtUtil.getToken(user .getUser(),"companyManager");break;
                    // 为 1的为公司管理员
                    case "2":  token = JwtUtil.getToken(user .getUser(),"companyworker");break;
                    // 为 2 为公司工作人员
                    case "3":  token = JwtUtil.getToken(user .getUser(),"superManager");break;
                    //3 为超管
                    default:   token= JwtUtil.getToken(user .getUser(),"user");break;
                    //  初始角色
                }


                map.put("token",token );
                return map;
//                       {"MsgType":"login","User":"nnnnn","Status":"true/false"}
                //登陆成功
            } else {
map.put("Msg","密码错误");
                return map;
                //  ("密码错误");
            }

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
        Map map = new HashMap();

        // login code can not be null
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
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
                map.put("status", 1);
                map.put("Msg", "解密成功");
                JSONObject userInfoJSON = JSONObject.parseObject(result);
                System .out .println(result );
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                userInfo.put("telphone", userInfoJSON.get("telphone"));

                //System.out .println("ddddd是"+userInfoJSON.getString("openId") );
                try {
                    User user =userRepoistory.findByOpenId(userInfoJSON.getString("openId"));
                    if(user==null ){

                        userInfo.put("username", null);
                        userInfo.put("password",null);
                    }

                    userInfo.put("username", user .getUserName() );
                    userInfo.put("password",user .getPassword() );
                }
                catch (Exception e){
                    map.put("status", 0);
                    map.put("msg", "解密失败,请重新授权");
                    return map;

                }
                map.put("userInfo", userInfo);


                return map;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败,请重新授权");
        return map;
    }


//    @Override
//    public User searchInfo(String userName) throws ParseException {
////Map map =new HashMap() ;
//        //map .put("status",false) ;
//        boolean status = false;//查询是否成功的布尔变量
//        User user = null;
//        user = userRepoistory.findByUser(userName );//数据库寻找user并赋值
//        if (user != null) {
//            status = true;
//            return user;
//        } else {
//            return null;
//        }
//
//    }

    }



}