package com.mort.easyllm.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Service.WorkFlow.WorkFlowService;
import com.mort.easyllm.Pojo.dto.WorkFlowDTO;
import com.mort.easyllm.Utils.NodeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private WorkFlowService workFlowService;


    @PostMapping("/addWorkFlow")
    public String addNode(@RequestBody WorkFlowDTO workFlowDTO){
        workFlowService.createWorkFlow(workFlowDTO);
        return "success";
    }

    @PostMapping("/getNodeConfig")
    public String getNodeConfig(){
        return JSONObject.toJSONString(NodeFactory.FRONT_PAGE_CONFIG);
    }


}
