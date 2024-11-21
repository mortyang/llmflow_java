package com.mort.easyllm.main.controller;


import com.mort.easyllm.main.pojo.Result;
import com.mort.easyllm.main.pojo.dto.ChatRequestDto;
import com.mort.easyllm.main.pojo.vo.SessionResponseVo;
import com.mort.easyllm.main.service.run.RunWorkFlowServiceImpl;
import com.mort.easyllm.main.service.workFlow.WorkFlowServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
@RequestMapping("/running")
@CrossOrigin(origins = "*")
@Slf4j
public class RunningController {

    @Autowired
    private RunWorkFlowServiceImpl runWorkFlowService;


    @PostMapping("/{id}")
    public Result<SessionResponseVo> runById(@PathVariable int id, @RequestBody ChatRequestDto chatRequestDto) {
        return runWorkFlowService.runWorkFlow(chatRequestDto, id);
    }

    @PostMapping("/stream/{id}")
    public Result<SessionResponseVo> runByIdWithStream(@PathVariable int id, @RequestBody ChatRequestDto chatRequestDto, HttpServletRequest request) {
        return runWorkFlowService.runWorkFlow(chatRequestDto, id);
    }


//    @GetMapping("/testRunNodeSSE/{param}")
//    public SseEmitter streamSseMvc(@PathVariable String param, HttpServletRequest request) {
//        SseEmitter emitter = new SseEmitter();
//        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
//
//        sseMvcExecutor.execute(() -> {
//            try {
//
//                // 使用 streamSession 逐步推送数据
//                streamSession(properties, messages, content -> {
//                    try {
//                        emitter.send(SseEmitter.event().data(content));
//                    } catch (IOException e) {
//                        emitter.completeWithError(e);
//                    }
//                });
//
//                emitter.send(SseEmitter.event().data(jsonResponse).mediaType(MediaType.APPLICATION_JSON));
//
//                // 完成 SSE 流
//                emitter.complete();
//            } catch (Exception ex) {
//                log.error("发生错误,error", ex);
//                emitter.completeWithError(ex);
//            }
//        });
//
//        return emitter;
//    }

}
