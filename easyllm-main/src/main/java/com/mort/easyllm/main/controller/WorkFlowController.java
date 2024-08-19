package com.mort.easyllm.main.controller;

import com.alibaba.fastjson2.JSONObject;

import com.mort.easyllm.common.enums.StatusEnum;
import com.mort.easyllm.common.pojo.Result;
import com.mort.easyllm.workflow.pojo.dto.WorkFlowDTO;
import com.mort.easyllm.main.mapper.WorkFlowMapper;
import com.mort.easyllm.main.mapper.po.WorkFlowPo;
import com.mort.easyllm.main.service.workFlow.WorkFlowServiceImpl;
import com.mort.easyllm.workflow.Node.runnableNode.NodeFactory;
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


    @GetMapping("/testRunNodeSSE/{param}")
    public SseEmitter streamSseMvc(@PathVariable String param, HttpServletRequest request) {
        SseEmitter emitter = new SseEmitter();
        String customHeader = request.getHeader("Custom-Header");
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data("SSE MVC - " + LocalTime.now().toString() + customHeader + param)
                            .id(String.valueOf(i))
                            .name("sse event - mvc");
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                log.error("error",ex);
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }


}
