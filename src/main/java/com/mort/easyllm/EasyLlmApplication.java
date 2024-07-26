package com.mort.easyllm;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Service.WorkFlow.WorkFlowService;
import com.mort.easyllm.Utils.NodeFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class EasyLlmApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyLlmApplication.class, args);
        NodeFactory.scanAndRegisterNodes();
    }

}
