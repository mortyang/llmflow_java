package com.mort.easyllm.main.service.run;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.common.context.SessionContext;
import com.mort.easyllm.common.enums.StatusEnum;
import com.mort.easyllm.common.parameter.SessionData;
import com.mort.easyllm.main.context.RunningWorkFlow;
import com.mort.easyllm.main.pojo.Result;
import com.mort.easyllm.main.pojo.dto.ChatRequestDto;
import com.mort.easyllm.main.pojo.vo.SessionResponseVo;
import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import com.mort.easyllm.workflow.service.WorkFlowRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class RunWorkFlowServiceImpl {


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private WorkFlowRunner workFlowRunner;




    public Result<SessionResponseVo> runWorkFlow(ChatRequestDto chatRequestDto, int id) {
        try {
            InfoNode runNode = RunningWorkFlow.RunningWorkFlowMap.get(id);
            if (runNode == null) {
                return Result.<SessionResponseVo>builder()
                        .message(StatusEnum.ERROT.getMessage())
                        .code(StatusEnum.ERROT.getCode())
                        .build();
            }

            if (chatRequestDto.getSessionId() == null) {
                UUID uuid = UUID.randomUUID();
                chatRequestDto.setSessionId(uuid.toString());
            }

            SessionData sessionData;
            if (!Boolean.TRUE.equals(redisTemplate.hasKey(chatRequestDto.getSessionId()))) {
                sessionData = SessionData.builder()
                        .sessionVariables(new HashMap<>())
                        .historyMessages(new ArrayList<>())
                        .sessionId(chatRequestDto.getSessionId())
                        .build();
            } else {
                //TODO兜底
                sessionData = JSONObject.parseObject((String) redisTemplate.opsForValue().get(chatRequestDto.getSessionId()), SessionData.class);
            }
            SessionContext.setSessionDataThreadLocal(sessionData);

            String res = workFlowRunner.runWorkFlow(runNode, chatRequestDto.getMessage());


            redisTemplate.opsForValue().set(chatRequestDto.getSessionId(), JSONObject.toJSONString(SessionContext.getSessionDataThreadLocal()));
            SessionContext.getSessionDataThreadLocal().getSessionVariables().clear();

            return Result.<SessionResponseVo>builder()
                    .message(StatusEnum.SUCCESS.getMessage())
                    .code(StatusEnum.SUCCESS.getCode())
                    .data(SessionResponseVo.builder()
                            .text(res)
                            .sessionId(chatRequestDto.getSessionId())
                            .sessionData(SessionContext.getSessionDataThreadLocal())
                            .build())
                    .build();
        } finally {
            SessionContext.removeSessionContext();
        }
    }


}
