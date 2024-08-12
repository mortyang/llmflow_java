package com.mort.easyllm.controller;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.pojo.Result;
import com.mort.easyllm.service.workFlow.WorkFlowServiceImpl;
import com.mort.easyllm.pojo.dto.WorkFlowDTO;
import com.mort.easyllm.workFlow.Node.runnableNode.NodeFactory;
import com.mort.easyllm.enums.StatusEnum;
import com.mort.easyllm.mapper.WorkFlowMapper;
import com.mort.easyllm.mapper.po.WorkFlowPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workFlow")
@CrossOrigin(origins = "*")
public class WorkFlowController {

    @Autowired
    private WorkFlowServiceImpl workFlowServiceImpl;

    @Autowired
    private WorkFlowMapper workFlowMapper;


    @PostMapping("/getNodeConfig")
    public String getNodeConfig() {
        return JSONObject.toJSONString(NodeFactory.FRONT_PAGE_CONFIG);
    }


    @PostMapping("/addWorkflow")
    public Result<String> addWorkflow(@RequestParam String workFlowName) {
        workFlowMapper.addWorkFlow(workFlowName);
        return Result.<String>builder()
                .code(StatusEnum.SUCCESS.getCode())
                .message(StatusEnum.SUCCESS.getMessage())
                .build();
    }


    @PostMapping("/deleteWorkflow")
    public Result<String> deleteWorkflow(@RequestParam int id) {
        workFlowMapper.deleteWorkFlow(id);
        return Result.<String>builder()
                .code(StatusEnum.SUCCESS.getCode())
                .message(StatusEnum.SUCCESS.getMessage())
                .build();
    }


    @PostMapping("/getWorkflowList")
    public Result<List<WorkFlowPo>> getWorkflowList() {
        return Result.<List<WorkFlowPo>>builder()
                .code(StatusEnum.SUCCESS.getCode())
                .message(StatusEnum.SUCCESS.getMessage())
                .data(workFlowMapper.getWorkFlowList())
                .build();
    }


    @PostMapping("/getWorkflowJson")
    public Result<String> getWorkflowJson(@RequestParam int id) {
        return Result.<String>builder()
                .code(StatusEnum.SUCCESS.getCode())
                .message(StatusEnum.SUCCESS.getMessage())
                .data(workFlowMapper.getWorkFlowJson(id))
                .build();
    }


    @PostMapping("/uploadWorkFlow")
    public Result<String> uploadWorkFlow(@RequestBody WorkFlowDTO workFlowDTO) {
        workFlowServiceImpl.uploadWorkFlow(workFlowDTO);
        return Result.<String>builder()
                .code(StatusEnum.SUCCESS.getCode())
                .message(StatusEnum.SUCCESS.getMessage())
                .build();
    }

}
