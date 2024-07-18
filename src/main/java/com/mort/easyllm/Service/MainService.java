package com.mort.easyllm.Service;

import com.alibaba.fastjson2.JSONObject;
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

    public void addWorkFlow(String json) throws IOException {
        WorkFlowDTO workFlowDTO = JSONObject.parseObject(json, WorkFlowDTO.class);
        InfoNode startNode =  workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFLowList());
        System.out.println(startNode.toString());
    }

}
