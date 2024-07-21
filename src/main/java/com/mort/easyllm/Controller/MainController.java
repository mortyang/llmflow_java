package com.mort.easyllm.Controller;

import com.mort.easyllm.Service.WorkFlow.WorkFlowService;
import com.mort.easyllm.Pojo.dto.WorkFlowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

    @Autowired
    private WorkFlowService workFlowService;


    @PostMapping("/addWorkFlow")
    public String addNode(@RequestBody WorkFlowDTO workFlowDTO){
        workFlowService.createWorkFlow(workFlowDTO);
        return "success";
    }


}
