package com.mort.easyllm.main;

import com.mort.easyllm.main.context.RunningWorkFlow;
import com.mort.easyllm.workflow.Node.runnableNode.NodeFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@SpringBootApplication
@ComponentScans({
        @ComponentScan("com.mort.easyllm.common"),
        @ComponentScan("com.mort.easyllm.workflow"),
        @ComponentScan("com.mort.easyllm.llm")
})
public class EasyLLMMainApplication {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(EasyLLMMainApplication.class, args);
        NodeFactory.scanAndRegisterNodes();
        run.getBean(RunningWorkFlow.class).initRunningWorkFlow();
    }

}
