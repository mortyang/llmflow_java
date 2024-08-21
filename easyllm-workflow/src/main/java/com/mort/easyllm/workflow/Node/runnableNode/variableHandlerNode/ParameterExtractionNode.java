package com.mort.easyllm.workflow.Node.runnableNode.variableHandlerNode;

import com.mort.easyllm.common.annotation.node.Node;
import com.mort.easyllm.common.annotation.node.PropertiesInject;
import com.mort.easyllm.workflow.Node.runnableNode.NormalRunnableNode;
import com.mort.easyllm.workflow.Node.runnableNode.variableHandlerNode.properties.ParameterExtractionNodeProperties;

@Node(nodeType = "ParameterExtractionNode")
public class ParameterExtractionNode implements NormalRunnableNode {


    @PropertiesInject
    private ParameterExtractionNodeProperties properties;


    /**
     * @param input 默认输入
     * @return output
     */
    @Override
    public String run(String input) {
        if(input.isEmpty()){
            input = properties.getInput().getString();
        }
        return "";
    }


}
