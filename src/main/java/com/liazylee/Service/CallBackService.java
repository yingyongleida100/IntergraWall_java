package com.liazylee.Service;

import com.liazylee.Mysql.AppAgentFacede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.text.SimpleDateFormat;
import java.util.Date;


import static java.lang.Thread.sleep;

/**
 * Created by li on 7/11/17.
 */
@Configuration
@EnableScheduling
public class CallBackService {
    @Autowired
    AppAgentFacede appAgentFacade;

    @Scheduled(cron = "0/5 * * * * ?")
    public void DoJob() {


        try {
            appAgentFacade.CallBackUserAgents();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
