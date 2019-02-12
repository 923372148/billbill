package com.gzcc.bill.Controller;

import com.gzcc.bill.Repoistory.PersonalBillRepoistory;
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
    private PersonalBillRepoistory personalBillRepoistory;
    @RequestMapping(value="/setPersonalBill")
    @ResponseBody
    public Map setPersonalBill(HttpServlet request, String personalBillId){
        ObjectId objectId=new ObjectId(personalBillId);
        personalBillRepoistory.findById(personalBillId);
        return  new HashMap();
    }
}
