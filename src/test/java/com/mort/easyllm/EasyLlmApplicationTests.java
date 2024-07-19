package com.mort.easyllm;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mort.easyllm.Node.InfoNode.InfoNode;
import com.mort.easyllm.Service.MainService;
import com.mort.easyllm.Utils.KryoThreadLocal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
                        "url": "url11",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "nextNodeName": "judge1"
                    },
                    {
                      "nodeName": "judge1",
                      "nodeType": "NormalJudgeNode",
                      "isBranchNode": true,
                      "properties": {},
                      "fatherNodeName": "http1",
                      "nextNodesName": ["http2", "http3","http10", "http11","http12", "http13","http14", "http15"]
                    },
                    {
                      "nodeName": "http1",
                      "nodeType": "HttpNode",
                      "isBranchNode": false,
                      "properties": {
                        "url": "url11",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "fatherNodeName": "judge1"
                    },
                    {
                      "nodeName": "http10",
                      "nodeType": "HttpNode",
                      "isBranchNode": false,
                      "properties": {
                        "url": "url11",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "fatherNodeName": "judge1"
                    },
                    {
                      "nodeName": "http11",
                      "nodeType": "HttpNode",
                      "isBranchNode": false,
                      "properties": {
                        "url": "url11",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "fatherNodeName": "judge1"
                    },
                    {
                      "nodeName": "http12",
                      "nodeType": "HttpNode",
                      "isBranchNode": false,
                      "properties": {
                        "url": "url11",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "fatherNodeName": "judge1"
                    },
                    {
                      "nodeName": "http13",
                      "nodeType": "HttpNode",
                      "isBranchNode": false,
                      "properties": {
                        "url": "url11",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "fatherNodeName": "judge1"
                    },
                    {
                      "nodeName": "http14",
                      "nodeType": "HttpNode",
                      "isBranchNode": false,
                      "properties": {
                        "url": "url11",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "fatherNodeName": "judge1"
                    },
                    {
                      "nodeName": "http15",
                      "nodeType": "HttpNode",
                      "isBranchNode": false,
                      "properties": {
                        "url": "url11",
                        "body": "asdabhjsdgahdkahdkjahdkjbckbaijeb那块加拿大那肯ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定ijeb那块加拿大那肯定定你看点卡阿三大苏打"
                      },
                      "fatherNodeName": "judge1"
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
                      "nextNodeName": "judge2"
                    },
                    {
                      "nodeName": "judge2",
                      "nodeType": "NormalJudgeNode",
                      "isBranchNode": true,
                      "properties": {},
                      "fatherNodeName": "http3",
                      "nextNodesName": ["http5", "http6"]
                    },
                    {
                      "nodeName": "http5",
                      "nodeType": "HttpNode",
                      "isBranchNode": false,
                      "properties": {
                        "url": "url11"
                      },
                      "fatherNodeName": "judge2",
                      "nextNodeName": null
                    },
                    {
                      "nodeName": "http6",
                      "nodeType": "HttpNode",
                      "isBranchNode": false,
                      "properties": {
                        "url": "url11"
                      },
                      "fatherNodeName": "judge2",
                      "nextNodeName": null
                    }
                  ]
                }
                """;
        InfoNode s =  mainService.addWorkFlow(json);

        mainService.runWorkFlow(s, "123");

//        KryoThreadLocal.getKryo().setRegistrationRequired(false);
//        Output output = new Output(new FileOutputStream("file.bin"));
//        KryoThreadLocal.getKryo().writeObject(output,s);
//        output.close();
//        Input input = new Input(new FileInputStream("file.bin"));
//        InfoNode n = KryoThreadLocal.getKryo().readObject(input, InfoNode.class);
//        input.close();



    }

}
