package com.gzcc.bill;

import com.gzcc.bill.Repoistory.KindRepoistory;
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
private KindRepoistory kindRepoistory;
static String[] str={"生活经费","娱乐消费","知识投资"};
    @Test
    public void contextLoads() {
        for (String a:str
             ) {
            Kind kind=new Kind(a,true);
            kindRepoistory.save(kind);
        }




    }

}

