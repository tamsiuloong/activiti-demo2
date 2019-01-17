package com.nzhxty.activitidemo2;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiDemo2Application.class, args);
    }

}

