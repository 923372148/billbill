package com.gzcc.kindmanager.Controller;

import com.gzcc.kindmanager.Repository.PersonalBillRepository;
import com.gzcc.kindmanager.Repository.UserRepository;
import com.gzcc.kindmanager.Service.BillAccountService;
import com.gzcc.kindmanager.domain.PersonalBill;
import com.gzcc.kindmanager.domain.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BillController {
    @Autowired
    private PersonalBillRepository personalBillRepository;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private BillAccountService billAccountService;
    @RequestMapping(value="/setPersonalBill")
    @ResponseBody
    public Map setPersonalBill(HttpServlet request,String openId,String personalBillName ,String description){

Map map=billAccountService.newBill(openId,personalBillName , description);

        return  new HashMap();
    }
    @RequestMapping(value="/findDefalutAccount")
    @ResponseBody
    public PersonalBill findDefalutAccount(HttpServlet request, String openId){
     new ObjectId(openId);
      User user= userRepository.findByOpenId(openId);
        ObjectId objectId=user.getDefalutpersonalBillId();
   return personalBillRepository.findByPersonalBillId(objectId);

    }
    @RequestMapping(value="/newAccount")
    @ResponseBody
    public Map setnewAccount(HttpServlet request, String personalBillId,String kindId,String comment,long money,boolean ifIncome){

        Map map=billAccountService.newAccount(personalBillId, kindId, comment,money, ifIncome);

        return  map;
    }
}
