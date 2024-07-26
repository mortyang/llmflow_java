package com.mort.easyllm;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.Pojo.dto.WorkFlowDTO;
import com.mort.easyllm.Service.WorkFlow.WorkFlowService;
import com.mort.easyllm.Utils.NodeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class EasyLlmApplicationTests {

    @Autowired
    WorkFlowService workFlowService;

    @Test
    void contextLoads() {
        String json = """
                {
                  "workFlowName": "123",
                  "workFLowList": [
                    {
                      "nodeName": "llmjudge_1",
                      "nodeType": "IntentionJudgeNode",
                      "properties": {
                        "intentions": ["查天气", "查快递"]
                      },
                      "nextNodeName": "judge1",
                      "isBranchNode": false
                    },
                    {
                      "nodeName": "judge1",
                      "nodeType": "NormalJudgeNode",
                      "isBranchNode": true,
                      "properties": {
                        "conditionToNodeMap": {
                          "查天气": "http2",
                          "查快递": "http3"
                        }
                      },
                      "fatherNodeNameList": ["llmjudge_1"],
                      "defaultNodeName": "llm1",
                      "nextNodeNameList": ["http2", "http3", "llm1"]
                    },
                    {
                      "nodeName": "http2",
                      "nodeType": "HttpNode",
                      "properties": {
                        "url": "http://127.0.0.1:8080/test",
                        "method": "get",
                        "body": "今天天气晴朗，37度，多云无风"
                      },
                      "fatherNodeNameList": ["judge1"],
                      "isBranchNode": false,
                      "nextNodeName": "llm2"
                    },
                    {
                      "nodeName": "http3",
                      "nodeType": "HttpNode",
                      "properties": {
                        "url": "http://127.0.0.1:8080/test",
                        "method": "post",
                        "body": "快递当前位置在杭州"
                      },
                      "fatherNodeNameList": ["judge1"],
                      "nextNodeName": "",
                      "isBranchNode": false
                    },
                    {
                      "nodeName": "llm1",
                      "nodeType": "TongyiNode",
                      "properties": {
                        "sysMsg":"没有判断出意图，安抚一下用户吧"
                      },
                      "fatherNodeNameList": ["judge1"],
                      "isBranchNode": false
                    },
                    {
                        "nodeName": "llm2",
                        "nodeType": "TongyiNode",
                        "properties": {
                          "sysMsg":"你是一位天气播报员，下面我将给出天气接口返回的天气信息，请告诉用户今天天气如何并提供合适的建议"
                        },
                        "fatherNodeNameList": ["http2"],
                        "isBranchNode": false
                      },
                      {
                        "nodeName": "llm3",
                        "nodeType": "TongyiNode",
                        "properties": {
                          "sysMsg":"你是一位快递查询员，告诉用户快递到哪了，安抚客户别急"
                        },
                        "fatherNodeNameList": ["http3"],
                        "isBranchNode": false
                      }
                  ]
                }
                """;
        NodeFactory.scanAndRegisterNodes();
        InfoNode s = workFlowService.createWorkFlow(JSONObject.parseObject(json, WorkFlowDTO.class));
        System.out.println(workFlowService.runWorkFlow(s, "查查我的快递到哪了"));

//        Output output = new Output(new FileOutputStream("file.bin"));
//        KryoThreadLocal.getKryo().writeObject(output,s);
//        output.close();
//        Input input = new Input(new FileInputStream("file.bin"));
//        InfoNode n = KryoThreadLocal.getKryo().readObject(input, InfoNode.class);
//        input.close();

    }

}
