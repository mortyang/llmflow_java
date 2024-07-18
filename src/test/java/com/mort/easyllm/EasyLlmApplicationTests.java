package com.mort.easyllm;

import com.mort.easyllm.Service.MainService;
import com.mort.easyllm.pojo.dto.WorkFlowDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class EasyLlmApplicationTests {

    @Autowired
    MainService mainService;

    @Test
    void contextLoads() throws IOException {
        String json = """
                {
                   "workFlowName": "123",
                   "workFLowList": [
                     {
                       "nodeName": "http1",
                       "nodeType": "HttpNode",
                       "isBranchNode": false,
                       "properties": {
                         "url": "url11"
                       },
                       "fatherNodeName": "http1",
                       "nextNodeName": "http1"
                     },
                     {
                       "nodeName": "judge1",
                       "nodeType": "NormalJudgeNode",
                       "isBranchNode": true,
                       "properties": {},
                       "fatherNodeName": "http1",
                       "nextNodesName": ["http2", "http3"]
                     },
                     {
                       "nodeName": "http2",
                       "nodeType": "HttpNode",
                       "isBranchNode": false,
                       "properties": {
                         "url": "url11"
                       },
                       "fatherNodeName": "judge1",
                       "nextNodeName": null
                     },
                     {
                       "nodeName": "http3",
                       "nodeType": "HttpNode",
                       "isBranchNode": false,
                       "properties": {
                         "url": "url11"
                       },
                       "fatherNodeName": "judge1",
                       "nextNodeName": null
                     }
                   ]
                 }
                """;
        mainService.addWorkFlow(json);
    }

}
