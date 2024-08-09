package com.mort.easyllm.Service.WorkFlow;

import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.Utils.KryoUtil;
import com.mort.easyllm.mapper.WorkFlowMapper;
import com.mort.easyllm.mapper.po.WorkFlowPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Mort
 */
@Repository
@Slf4j
public class RunningWorkFlow {

    @Autowired
    private WorkFlowMapper workFlowMapper;


    public static Map<Integer, InfoNode> RunningWorkFlowMap = new ConcurrentHashMap<>();


    public void initRunningWorkFlow() {
        List<WorkFlowPo> enabledWorkFlow = workFlowMapper.getAllEnabledWorkFlowName();
        for (WorkFlowPo workFlowPo : enabledWorkFlow) {
            RunningWorkFlowMap.put(workFlowPo.getId(), KryoUtil.deserialize(workFlowPo.getRunnableObjectByte(), InfoNode.class));
        }
        log.info("初始化成功当前map：${}", RunningWorkFlowMap.toString());
    }

}
