package com.mort.easyllm.web.controller;


import com.mort.easyllm.workFlow.Node.chainNode.InfoNode;
import com.mort.easyllm.pojo.Result;
import com.mort.easyllm.pojo.dto.ChatRequestDto;
import com.mort.easyllm.context.RunningWorkFlow;
import com.mort.easyllm.web.service.workFlow.WorkFlowServiceImpl;
import com.mort.easyllm.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/running")
@CrossOrigin(origins = "*")
public class RuningController {

    @Autowired
    private RunningWorkFlow runningWorkFlow;

    @Autowired
    private WorkFlowServiceImpl workFlowServiceImpl;


    @PostMapping("/{id}")
    public Result<String> runById(@PathVariable int id, @RequestBody ChatRequestDto chatRequestDto) {
        InfoNode runNode = RunningWorkFlow.RunningWorkFlowMap.get(id);
        if (runNode != null) {
            return Result.<String>builder()
                    .message(StatusEnum.SUCCESS.getMessage())
                    .code(StatusEnum.SUCCESS.getCode())
                    .data(workFlowServiceImpl.runWorkFlow(runNode, chatRequestDto.getMessage())).build();
        }
        return Result.<String>builder()
                .message(StatusEnum.ERROT.getMessage())
                .code(StatusEnum.ERROT.getCode())
                .data("不存在的flow").build();
    }


}
