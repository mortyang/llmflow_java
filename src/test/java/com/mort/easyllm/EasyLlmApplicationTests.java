package com.mort.easyllm;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.Pojo.dto.WorkFlowDTO;
import com.mort.easyllm.Service.WorkFlow.WorkFlowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class EasyLlmApplicationTests {

    @Autowired
    WorkFlowService workFlowService;

    @Test
    void contextLoads() throws IOException {
        String json = """
                {
                  "workFlowName": "123",
                  "workFLowList": [
                    {
                      "nodeName": "http1",
                      "nodeType": "HttpNode",
                      "properties": {
                        "method":"post",
                        "url": "http://127.0.0.1:8080/test",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打",
                      },
                      "fatherNodeNameList": ["http1"],
                      "nextNodeName": "judge1",
                      "isBranchNode": false
                    },
                    {
                      "nodeName": "judge1",
                      "nodeType": "NormalJudgeNode",
                      "isBranchNode": true,
                      "properties": {},
                      "fatherNodeNameList": ["http1"],
                      "defaultNodeName":"http2",
                      "nextNodesName": ["http2", "http3"]
                    },
                    {
                      "nodeName": "http2",
                      "nodeType": "HttpNode",
                      "properties": {
                        "url": "http://127.0.0.1:8080/test",
                        "method":"get",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "fatherNodeNameList": ["judge1"],
                      "isBranchNode": false
                    },
                    {
                      "nodeName": "http3",
                      "nodeType": "HttpNode",
                      "properties": {
                        "url": "http://127.0.0.1:8080/test",
                        "method":"post",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "fatherNodeNameList": ["judge1"],
                      "isBranchNode": false
                    }
                  ]
                }
                """;
        InfoNode s = workFlowService.createWorkFlow(JSONObject.parseObject(json, WorkFlowDTO.class));
        System.out.println(workFlowService.runWorkFlow(s, "123"));

//        Output output = new Output(new FileOutputStream("file.bin"));
//        KryoThreadLocal.getKryo().writeObject(output,s);
//        output.close();
//        Input input = new Input(new FileInputStream("file.bin"));
//        InfoNode n = KryoThreadLocal.getKryo().readObject(input, InfoNode.class);
//        input.close();

    }

}
