package com.mort.easyllm.workflow.Node.runnableNode.variableNode;

import com.mort.easyllm.common.context.SessionContext;
import com.mort.easyllm.common.parameter.Message;
import com.mort.easyllm.llm.supplier.tongyi.impl.Tongyi;
import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import com.mort.easyllm.workflow.Node.runnableNode.NormalRunnableNode;
import com.mort.easyllm.workflow.Node.runnableNode.variableNode.properties.ParameterExtractionNodeProperties;
import com.mort.easyllm.workflow.annotation.node.Node;
import com.mort.easyllm.workflow.annotation.node.PropertiesInject;
import com.mort.easyllm.workflow.parameter.ExtractionParameter;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Node(nodeType = "ParameterExtractionNode")
public class ParameterExtractionNode implements NormalRunnableNode {


    @PropertiesInject
    private ParameterExtractionNodeProperties properties;


    @Override
    public String run(InfoNode infoNode, Consumer<String> callback) {
        Tongyi tongyi = new Tongyi();
        properties.getLlmProperties().setUseContext(true);

        StringBuilder missingParameter = new StringBuilder();
        for (ExtractionParameter parameter : properties.getExtractionParameterList()) {
            List<Message> list = new ArrayList<>();
            list.add(Message.builder()
                    .role(Message.Role.system.getRoleName())
                    .text("你是一个参数提取器，只需要回复我用户对应提到的：" + parameter.getParameterName()
                            + "信息,参数的描述为：" + parameter.getDescription() + "，若用户的消息中未提到" + parameter.getParameterName() + "请回复：未找到")
                    .build());
            list.add(Message.builder().role(Message.Role.user.getRoleName()).text(properties.getInput().getString()).build());
            String llmMessage = tongyi.fullSession(properties.getLlmProperties(), list);

            //变量没找到且必选且之前没提到则报缺失
            if ((Objects.equals(llmMessage, "未找到") || !parameter.getParameterType().validate(llmMessage)) && parameter.getRequired()) {
                if (SessionContext.getVariableAtLocation(parameter.getParameterName(), SessionContext.StorageLocation.SESSION) == null) {
                    missingParameter.append(parameter.getParameterName()).append(",");
                    continue;
                }
            }
            SessionContext.putSessionVariable(infoNode.getNodeName(), parameter.getParameterName(), llmMessage);
        }

        if (!missingParameter.isEmpty()) {
            SessionContext.putGlobalVariable(infoNode.getNodeName(), "is_success", "0");
            SessionContext.putGlobalVariable(infoNode.getNodeName(), "missingParameter", missingParameter.toString());
        } else {
            SessionContext.putGlobalVariable(infoNode.getNodeName(), "is_success", "1");
        }

        return missingParameter.toString();
    }

}
