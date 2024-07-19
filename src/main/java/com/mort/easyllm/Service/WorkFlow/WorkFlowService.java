package com.mort.easyllm.Service.WorkFlow;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.Pojo.dto.WorkFlowDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorkFlowService {

    @Autowired
    private WorkFlowBuilder workFlowBuilder;

    public InfoNode addWorkFlowTest(String json) {
        WorkFlowDTO workFlowDTO = JSONObject.parseObject(json, WorkFlowDTO.class);
        return workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFLowList());
    }

    public InfoNode addWorkFlow(WorkFlowDTO workFlowDTO) {
        return workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFLowList());
    }

    public String runWorkFlow(InfoNode startNode, String userInput) {
        InfoNode nodeNow = startNode;
        startNode.setInput(userInput);
        while (true) {
            log.info("开始执行：{},input:{}",nodeNow.getNodeName(),nodeNow.getInput());
            nodeNow.runNode();
            log.info("执行结束：{},output:{}",nodeNow.getNodeName(),nodeNow.getOutput());
            if(nodeNow.getNextNode() == null){
                break;
            }
            nodeNow = nodeNow.getNextNode();
        }
        return nodeNow.getOutput();
    }


}
