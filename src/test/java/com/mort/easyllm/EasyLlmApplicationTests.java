package com.mort.easyllm;

import com.alibaba.fastjson2.JSONObject;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.Pojo.dto.WorkFlowDTO;
import com.mort.easyllm.Service.WorkFlow.WorkFlowService;
import com.mort.easyllm.Utils.KryoUtil;
import com.mort.easyllm.Utils.NodeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class EasyLlmApplicationTests {

    @Autowired
    WorkFlowService workFlowService;

    @Test
    void contextLoads() throws FileNotFoundException {
        String json = "{\"id\":\"10001\",\"workFlowList\":[{\"nodeName\":\"llmJudge_1\",\"nodeType\":\"IntentionJudgeNode\",\"properties\":{\"modelName\":\"\",\"hasProperties\":false,\"isBranchNode\":false,\"fatherNodeNameList\":[\"startNode\"],\"nextNodeName\":\"judge_2\",\"defaultNodeName\":\"\",\"nextNodeNameList\":[],\"intentions\":[\"\",null]},\"fatherNodeNameList\":[\"startNode\"],\"nextNodeName\":\"judge_2\",\"isBranchNode\":false,\"defaultNodeName\":\"\",\"nextNodeNameList\":[]},{\"nodeName\":\"judge_2\",\"nodeType\":\"NormalJudgeNode\",\"properties\":{\"isBranchNode\":true,\"defaultNodeName\":\"llm_5\",\"hasProperties\":false,\"fatherNodeNameList\":[\"llmJudge_1\"],\"nextNodeName\":\"\",\"nextNodeNameList\":[\"http_3\",\"http_4\",\"llm_5\"],\"nodeNameToConditionMap\":{\"http_3\":\"\",\"http_4\":\"\"}},\"fatherNodeNameList\":[\"llmJudge_1\"],\"nextNodeName\":\"\",\"isBranchNode\":true,\"defaultNodeName\":\"llm_5\",\"nextNodeNameList\":[\"http_3\",\"http_4\",\"llm_5\"]},{\"nodeName\":\"http_3\",\"nodeType\":\"HttpNode\",\"properties\":{\"url\":\"\",\"method\":\"get\",\"body\":\"\",\"hasProperties\":false,\"isBranchNode\":false,\"fatherNodeNameList\":[\"judge_2\"],\"nextNodeName\":\"llm_0\",\"defaultNodeName\":\"\",\"nextNodeNameList\":[],\"headers\":{}},\"fatherNodeNameList\":[\"judge_2\"],\"nextNodeName\":\"llm_0\",\"isBranchNode\":false,\"defaultNodeName\":\"\",\"nextNodeNameList\":[]},{\"nodeName\":\"llm_0\",\"nodeType\":\"TongyiNode\",\"properties\":{\"modelName\":\"\",\"sysMsg\":\"\",\"hasProperties\":false,\"isBranchNode\":false,\"fatherNodeNameList\":[\"http_3\"],\"nextNodeName\":\"endNode\",\"defaultNodeName\":\"\",\"nextNodeNameList\":[]},\"fatherNodeNameList\":[\"http_3\"],\"nextNodeName\":\"endNode\",\"isBranchNode\":false,\"defaultNodeName\":\"\",\"nextNodeNameList\":[]},{\"nodeName\":\"http_4\",\"nodeType\":\"HttpNode\",\"properties\":{\"url\":\"\",\"method\":\"get\",\"body\":\"\",\"hasProperties\":false,\"isBranchNode\":false,\"fatherNodeNameList\":[\"judge_2\"],\"nextNodeName\":\"llm_6\",\"defaultNodeName\":\"\",\"nextNodeNameList\":[],\"headers\":{}},\"fatherNodeNameList\":[\"judge_2\"],\"nextNodeName\":\"llm_6\",\"isBranchNode\":false,\"defaultNodeName\":\"\",\"nextNodeNameList\":[]},{\"nodeName\":\"llm_6\",\"nodeType\":\"TongyiNode\",\"properties\":{\"modelName\":\"\",\"sysMsg\":\"\",\"hasProperties\":false,\"isBranchNode\":false,\"fatherNodeNameList\":[\"http_4\"],\"nextNodeName\":\"endNode\",\"defaultNodeName\":\"\",\"nextNodeNameList\":[]},\"fatherNodeNameList\":[\"http_4\"],\"nextNodeName\":\"endNode\",\"isBranchNode\":false,\"defaultNodeName\":\"\",\"nextNodeNameList\":[]},{\"nodeName\":\"llm_5\",\"nodeType\":\"TongyiNode\",\"properties\":{\"modelName\":\"\",\"sysMsg\":\"\",\"hasProperties\":false,\"isBranchNode\":false,\"fatherNodeNameList\":[\"judge_2\"],\"nextNodeName\":\"endNode\",\"defaultNodeName\":\"\",\"nextNodeNameList\":[]},\"fatherNodeNameList\":[\"judge_2\"],\"nextNodeName\":\"endNode\",\"isBranchNode\":false,\"defaultNodeName\":\"\",\"nextNodeNameList\":[]}]}";
        NodeFactory.scanAndRegisterNodes();
        InfoNode s = workFlowService.createWorkFlow(JSONObject.parseObject(json, WorkFlowDTO.class));
        System.out.println(s.toString());
//        System.out.println(workFlowService.runWorkFlow(s, "查查我的快递到哪了"));

    }

}
