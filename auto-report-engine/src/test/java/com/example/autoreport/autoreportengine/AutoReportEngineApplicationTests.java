package com.example.autoreport.autoreportengine;

import com.example.autoreport.autoreportengine.services.impl.RptDocTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoReportEngineApplicationTests {

    @Autowired
    RptDocTemplateService rptDocTemplateService;

    @Test
    public void contextLoads() {
        rptDocTemplateService.preview(1, false, null);
 //       System.out.println("hello");
    }

}
