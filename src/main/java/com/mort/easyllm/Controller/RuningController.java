package com.mort.easyllm.Controller;


import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.Pojo.Result;
import com.mort.easyllm.Pojo.dto.ChatRequestDto;
import com.mort.easyllm.Service.WorkFlow.RunningWorkFlow;
import com.mort.easyllm.Service.WorkFlow.WorkFlowService;
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
    private WorkFlowService workFlowService;


    @PostMapping("/{id}")
    public Result<String> runById(@PathVariable int id, @RequestBody ChatRequestDto chatRequestDto) {
        InfoNode runNode = RunningWorkFlow.RunningWorkFlowMap.get(id);
        if (runNode != null) {
            return Result.<String>builder()
                    .message(StatusEnum.SUCCESS.getMessage())
                    .code(StatusEnum.SUCCESS.getCode())
                    .data(workFlowService.runWorkFlow(runNode, chatRequestDto.getMessage())).build();
        }
        return Result.<String>builder()
                .message(StatusEnum.ERROT.getMessage())
                .code(StatusEnum.ERROT.getCode())
                .data("不存在的flow").build();
    }


}
