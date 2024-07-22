package com.mort.easyllm.Service.WorkFlow;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.Node.InfoNode.RunNodeReturn;
import com.mort.easyllm.Pojo.dto.WorkFlowDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorkFlowService {

    @Autowired
    private WorkFlowBuilder workFlowBuilder;

//    public InfoNode addWorkFlowTest(String json) {
//        WorkFlowDTO workFlowDTO = JSONObject.parseObject(json, WorkFlowDTO.class);
//        return workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFLowList());
//    }

    public InfoNode createWorkFlow(WorkFlowDTO workFlowDTO) {
        return workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFLowList());
    }

    public String runWorkFlow(InfoNode startNode, String userInput) {
        InfoNode nodeNow = startNode;
        String textTemp = userInput;
        while (true) {
            log.info("开始执行：{},input:{}",nodeNow.getNodeName(),textTemp);
            textTemp = nodeNow.runNode(textTemp);
            log.info("执行结束：{},output:{}",nodeNow.getNodeName(),textTemp);
            if(nodeNow.getNextNode() == null){
                break;
            }
            nodeNow = nodeNow.getNextNode();
        }
        return textTemp;
    }


}
