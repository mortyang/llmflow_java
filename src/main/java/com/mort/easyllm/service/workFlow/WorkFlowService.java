package com.mort.easyllm.service.workFlow;

import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.pojo.dto.WorkFlowDTO;
import com.mort.easyllm.utils.KryoUtil;
import com.mort.easyllm.mapper.WorkFlowMapper;
import com.mort.easyllm.mapper.po.WorkFlowPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mort
 */
@Service
@Slf4j
public class WorkFlowService {

    @Autowired
    private WorkFlowBuilder workFlowBuilder;

    @Autowired
    private WorkFlowMapper workFlowMapper;


    //TEST
//    public InfoNode addWorkFlowTest(String json) {
//        WorkFlowDTO workFlowDTO = JSONObject.parseObject(json, WorkFlowDTO.class);
//        return workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFLowList());
//    }


    public InfoNode createWorkFlow(WorkFlowDTO workFlowDTO) {
        return workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFlowList());
    }


    public void uploadWorkFlow(WorkFlowDTO workFlowDTO) {
        InfoNode s = workFlowBuilder.buildWorkFlow(workFlowDTO.getWorkFlowList());
        byte[] objectByte = KryoUtil.serialize(s);
        RunningWorkFlow.RunningWorkFlowMap.put(workFlowDTO.getId(), KryoUtil.deserialize(objectByte, InfoNode.class));
        workFlowMapper.uploadWorkFlow(WorkFlowPo.builder()
                .id(workFlowDTO.getId())
                .frontJson(workFlowDTO.getFrontJson())
                .runnableObjectByte(objectByte)
                .build());
    }


    public String runWorkFlow(InfoNode startNode, String userInput) {
        InfoNode nodeNow = startNode;
        String textTemp = userInput;
        while (true) {
            log.info("节点开始执行：{},input:{}", nodeNow.getNodeName(), textTemp);
            textTemp = nodeNow.runNode(textTemp);
            log.info("节点执行结束：{},output:{}", nodeNow.getNodeName(), textTemp);
            if (nodeNow.getNextNode() == null) {
                break;
            }
            nodeNow = nodeNow.getNextNode();
        }
        return textTemp;
    }


}
