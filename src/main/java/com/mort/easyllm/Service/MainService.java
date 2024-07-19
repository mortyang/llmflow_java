package com.mort.easyllm.Service;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Node.BranchNode.BranchNode;
import com.mort.easyllm.Node.InfoNode.BranchInfoNode;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.Utils.WorkFlowBuilder;
import com.mort.easyllm.pojo.dto.WorkFlowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MainService {

    @Autowired
    private WorkFlowBuilder workFlowBuilder;

    public InfoNode addWorkFlow(String json) throws IOException {
        WorkFlowDTO workFlowDTO = JSONObject.parseObject(json, WorkFlowDTO.class);
        InfoNode startNode = workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFLowList());
        System.out.println(startNode.toString());
        return startNode;
    }

    public void runWorkFlow(InfoNode startNode, String userInput) {
        InfoNode nodeNow = startNode;
        startNode.setInput(userInput);
        do {
            nodeNow.runNode();
            System.out.println(nodeNow.getNodeName());
            nodeNow = nodeNow.getNextNode();
        } while (nodeNow != null);
    }


}
