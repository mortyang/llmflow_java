package com.mort.easyllm.workflow.Node.runnableNode.llmNode;


import com.mort.easyllm.common.context.SessionContext;
import com.mort.easyllm.common.parameter.Message;
import com.mort.easyllm.llm.supplier.tongyi.impl.Tongyi;
import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import com.mort.easyllm.workflow.Node.runnableNode.NormalRunnableNode;
import com.mort.easyllm.workflow.Node.runnableNode.llmNode.properties.IntentionJudgeProperties;
import com.mort.easyllm.workflow.annotation.node.Node;
import com.mort.easyllm.workflow.annotation.node.PropertiesInject;
import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mort
 */
@Slf4j
@Node(nodeType = "IntentionJudgeNode")
public class IntentionJudgeNodeImpl implements NormalRunnableNode {

    private final IntentionJudgeProperties properties;

    private final String sysMsg;

    @PropertiesInject
    public IntentionJudgeNodeImpl(IntentionJudgeProperties properties) {
        this.properties = properties;
        this.sysMsg = generateSysMsg(this.properties.getIntentions());
    }

    private String generateSysMsg(List<String> intentions) {
        StringBuilder str = new StringBuilder();
        for (String intention : intentions) {
            str.append(intention);
            str.append(" ");
        }
        String intentionsStr = str.toString();
        return String.format("分析用户的输入，并检查是否是以下意图‘%s’。只有当用户输入的内容的意图包含‘%s’这些短语之一时，才返回‘%s’。如果用户输入内容中没有‘%s’这些意图，则返回‘无匹配",
                intentionsStr, intentionsStr, intentionsStr, intentionsStr);
    }

    /**
     * @return 节点输出
     */
    @Override
    public String run(InfoNode infoNode, Consumer<String> callback) {
        Tongyi tongyi = new Tongyi();
        String result = SessionContext.getVariableByName(infoNode.getNodeName());
        if (result == null) {
            List<Message> list = new ArrayList<>();
            list.add(Message.builder().role("system").text(sysMsg).build());
            list.add(Message.builder().role("user").text(properties.getInput().getString()).build());
            result = tongyi.fullSession(properties.getLlmProperties(), list);
            if (!Objects.equals(result, "无匹配")) {
                SessionContext.putSessionVariable(result, infoNode.getNodeName());
            }
        }
        log.info(result);
        return result;
    }

}
