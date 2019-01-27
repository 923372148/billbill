package com.gzcc.bill.Service;


import com.gzcc.bill.Repoistory.PersonalBillRepoistory;
import com.gzcc.bill.Repoistory.UserRepoistory;
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
    public String registUser(JSONObject jsonObject) {
/**
 *
 * 功能描述:
 *
 * @param: [jsonObject]
 * @return: java.lang.String
 * @auther: hopnetworks
 * @function: 注册操作函数
 */

        User user = new User();
        user.setUser(jsonObject.getString("username"));
        user.setPassword(jsonObject.getString("password"));
        if (userRepoistory.findByUser(jsonObject.getString("username")) == null) {
            System.out.println("user：" + user);
            userRepoistory.save(user);
            return "success";
        } else {
            return "用户名存在";
        }

    }
    @Override
    public Map regist(String userName, String password,int companyId,String userType) {
/**
 *
 * 功能描述:
 *
 * @param: [jsonObject]
 * @return: map
 * @auther: hopnetworks
 * @function: 注册操作函数
 */

Map map=new HashMap() ;
        if (userRepoistory.findByUser(userName ) == null) {
            User user = new User();
            user .setUser(userName );
            user.setPassword(password );
user.setStatus(true);
            user.setType("2");
if(userType!=null){
    user.setType(userType);
}
user.setLasttime(new Date());


//companyRepository.findById(companyId);
            Company company=companyRepository.findcompany(companyId);

user.setCompany(company);
            System.out.println("user：" + user);
            map .put("status",true) ;
            map .put("user_id", userRepoistory.save(user).getId() ) ;
            map .put("user_name", userName) ;
           return map;
        } else {

            map .put("status",false) ;
            map .put("Ms g","用户名相同");
            return map;
        }

    }

    @Override
    public User searchInfo(String userName) throws ParseException {
//Map map =new HashMap() ;
        //map .put("status",false) ;
        boolean status = false;//查询是否成功的布尔变量
        User user = null;
        user = userRepoistory.findByUser(userName );//数据库寻找user并赋值
        if (user != null) {
            status = true;
            return user;
        } else {
            return null;
        }

    }
    @Override
    public Map findUserBalance(String userName){/**
     *
     * 功能描述:
     *
     * @param: [userName]
     * @return: java.util.Map
     * @auther: hopnetworks
     * @function: 查询余额
     */
        Map map = new HashMap();
        map .put("status",false) ;
       try{
        User user = userRepoistory.findByUser(userName);
        if (user!=null) {
            map.put("status",true) ;
            map.put("User", user.getUser());
            map.put("Single",user .getCompany() .getBalance_s());
            map.put("Double",user.getCompany() .getBalance_d() );
            map.put("Movable",user.getCompany() .getBalance_mov() );
            map.put("Stationary",user.getCompany() .getBalance_sta() );
            map.put("DoubleView",user.getCompany() .getBalance_sta() );
return map;
        }
        else {
            return map;
        }

        }catch(Exception e){
           return map;
       }
    }



}