package com.gzcc.bill;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gzcc.bill.Repository.KindRepository;
import com.gzcc.bill.Service.KindService;
import com.gzcc.bill.domain.Kind;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillApplicationTests {
@Autowired
private KindRepository kindRepository;
    @Reference(version = "1.0.0")
    private KindService kindService;

static String[] str={"生活经费","娱乐消费","知识投资"};
    @Test
    public void contextLoads() {

        kindService.addKind("第一笔测试");


        for (String a:str
             ) {
           Kind kind=new Kind(a);
          kindRepository.save(kind);
        }




    }

}

