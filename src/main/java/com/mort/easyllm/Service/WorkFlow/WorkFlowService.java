package com.mort.easyllm.Service.WorkFlow;

import com.mort.easyllm.Context.NodeRunningContextThreadLocal;
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
        NodeRunningContextThreadLocal.getNodecontext().getNodeOutput().put("userInput",userInput);
        while (true) {
            log.info("节点开始执行：{},input:{}",nodeNow.getNodeName(),textTemp);
            textTemp = nodeNow.runNode(textTemp);
            log.info("节点执行结束：{},output:{}",nodeNow.getNodeName(),textTemp);
            if(nodeNow.getNextNode() == null){
                NodeRunningContextThreadLocal.removeNodecontext();
                break;
            }
            NodeRunningContextThreadLocal.getNodecontext().getNodeOutput().put(nodeNow.getNodeName(),textTemp);
            nodeNow = nodeNow.getNextNode();
        }
        return textTemp;
    }


}
