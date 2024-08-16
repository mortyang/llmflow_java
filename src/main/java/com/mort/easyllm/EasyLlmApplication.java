package com.mort.easyllm;

import com.mort.easyllm.workFlow.Node.runnableNode.NodeFactory;
import com.mort.easyllm.context.RunningWorkFlow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EasyLlmApplication {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(EasyLlmApplication.class, args);
        NodeFactory.scanAndRegisterNodes();
        run.getBean(RunningWorkFlow.class).initRunningWorkFlow();
    }

}
