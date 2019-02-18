package com.gzcc.bill.Controller;

import com.gzcc.bill.Repoistory.PersonalBillRepoistory;
import com.gzcc.bill.Repoistory.UserRepoistory;
import com.gzcc.bill.domain.PersonalBill;
import com.gzcc.bill.domain.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class BillController {
    @Autowired
    private PersonalBillRepoistory personalBillRepoistory;
    @Autowired
    private UserRepoistory userRepoistory ;
    @RequestMapping(value="/setPersonalBill")
    @ResponseBody
    public Map setPersonalBill(HttpServlet request, String personalBillId){
        ObjectId objectId=new ObjectId(personalBillId);

        personalBillRepoistory.findById(personalBillId);
        return  new HashMap();
    }
    @RequestMapping(value="/allAccount")
    @ResponseBody
    public PersonalBill searchAccount(HttpServlet request, String openId){
     new ObjectId(openId);
      User user= userRepoistory.findByOpenId(openId);
        ObjectId objectId=user.getPersonalBillId().get(0);
   return personalBillRepoistory.findByObjectId(objectId);

    }

}
