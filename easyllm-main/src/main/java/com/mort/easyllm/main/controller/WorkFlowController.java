package com.mort.easyllm.main.controller;


import com.mort.easyllm.common.enums.StatusEnum;
import com.mort.easyllm.main.pojo.Result;
import com.mort.easyllm.workflow.pojo.dto.WorkFlowDTO;
import com.mort.easyllm.main.mapper.WorkFlowMapper;
import com.mort.easyllm.main.mapper.po.WorkFlowPo;
import com.mort.easyllm.main.service.workFlow.WorkFlowServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/workFlow")
@CrossOrigin(origins = "*")
@Slf4j
public class WorkFlowController {

    @Autowired
    private WorkFlowServiceImpl workFlowServiceImpl;

    @Autowired
    private WorkFlowMapper workFlowMapper;


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
        workFlowServiceImpl.buildWorkFlow(workFlowDTO);
        return Result.<String>builder()
                .code(StatusEnum.SUCCESS.getCode())
                .message(StatusEnum.SUCCESS.getMessage())
                .build();
    }



}
