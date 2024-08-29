package com.mort.easyllm.main.service.workFlow;

import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import com.mort.easyllm.workflow.pojo.dto.WorkFlowDTO;
import com.mort.easyllm.common.utils.KryoUtil;
import com.mort.easyllm.main.mapper.WorkFlowMapper;
import com.mort.easyllm.main.mapper.po.WorkFlowPo;
import com.mort.easyllm.main.context.RunningWorkFlow;
import com.mort.easyllm.workflow.service.WorkFlowBuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Mort
 */
@Service
@Slf4j
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private WorkFlowBuilder workFlowBuilder;

    @Autowired
    private WorkFlowMapper workFlowMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    public InfoNode createWorkFlow(WorkFlowDTO workFlowDTO) {
        return workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFlowList());
    }

    public void buildWorkFlow(WorkFlowDTO workFlowDTO) {
        InfoNode s = workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFlowList());
        byte[] objectByte = KryoUtil.serialize(s);
        RunningWorkFlow.RunningWorkFlowMap.put(workFlowDTO.getId(), KryoUtil.deserialize(objectByte, InfoNode.class));
        workFlowMapper.uploadWorkFlow(WorkFlowPo.builder()
                .id(workFlowDTO.getId())
                .frontJson(workFlowDTO.getFrontJson())
                .runnableObjectByte(objectByte)
                .build());
    }

}
