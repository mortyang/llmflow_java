package com.mort.easyllm.main.context;

import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import com.mort.easyllm.common.utils.KryoUtil;
import com.mort.easyllm.main.mapper.WorkFlowMapper;
import com.mort.easyllm.main.mapper.po.WorkFlowPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mort
 */
@Repository
@Slf4j
public class RunningWorkFlow {


    private final WorkFlowMapper workFlowMapper;

    @Autowired
    public RunningWorkFlow(WorkFlowMapper workFlowMapper) {
        this.workFlowMapper = workFlowMapper;
    }


    public static Map<Integer, InfoNode> RunningWorkFlowMap = new ConcurrentHashMap<>();


    //TODO：构建失败时需要获取json再次构建
    public void initRunningWorkFlow() {
        List<WorkFlowPo> enabledWorkFlow = workFlowMapper.getAllEnabledWorkFlowName();
        for (WorkFlowPo workFlowPo : enabledWorkFlow) {
            RunningWorkFlowMap.put(workFlowPo.getId(), KryoUtil.deserialize(workFlowPo.getRunnableObjectByte(), InfoNode.class));
        }
        log.info("初始化成功当前map：${}", RunningWorkFlowMap.toString());
    }

}
