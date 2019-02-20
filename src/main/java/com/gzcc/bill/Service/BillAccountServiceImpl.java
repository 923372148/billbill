package com.gzcc.bill.Service;

import com.gzcc.bill.Repository.PersonalBillRepository;
import com.gzcc.bill.Repository.RunningAccountRepository;
import com.gzcc.bill.Repository.UserRepository;
import com.gzcc.bill.domain.PersonalBill;
import com.gzcc.bill.domain.RunningAccount;
import com.gzcc.bill.domain.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("billAccountService")
public class BillAccountServiceImpl implements BillAccountService {
    private final PersonalBillRepository personalBillRepository;
    private final UserRepository userRepository ;
    private final RunningAccountRepository runningAccountRepository;

@Autowired
    public BillAccountServiceImpl(PersonalBillRepository personalBillRepository, UserRepository userRepository, RunningAccountRepository runningAccountRepository) {
        this.personalBillRepository = personalBillRepository;
    this.userRepository = userRepository;
    this.runningAccountRepository = runningAccountRepository;
}

    @Override
    public Map newAccount(String personalBillId,String kindId,String comment,long money,boolean ifIncome) {
        PersonalBill personalBill=personalBillRepository.findByPersonalBillId(new ObjectId(personalBillId));
        RunningAccount runningAccount=new RunningAccount(money,ifIncome,comment,new Date());
        runningAccount.setKindName("生活类");
        personalBill.setIcome(personalBill.getIcome()+money);
        personalBill.getRunningAccountListId().add(runningAccountRepository.save(runningAccount).getRunningAccountId());
        personalBillRepository.save(personalBill);
        Map map=new HashMap();
        map.put("status",true);

        return map;
    }

    @Override
    public Map newBill(String openId,String personalBillName ,String description) {
    Map map=new HashMap();
    PersonalBill personalBill=new PersonalBill(personalBillName , description);
User user=userRepository.findByOpenId(openId);
personalBill.setIfDefault(false);
user.getPersonalBillId().add(personalBillRepository.save(personalBill).getPersonalBillId());
userRepository.save(user);
map.put("status",true);

        return map;
    }
}
