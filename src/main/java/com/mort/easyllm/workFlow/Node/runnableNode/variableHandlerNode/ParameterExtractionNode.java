package com.mort.easyllm.workFlow.Node.runnableNode.variableHandlerNode;

import com.mort.easyllm.workFlow.Node.runnableNode.NormalRunnableNode;
import com.mort.easyllm.workFlow.Node.runnableNode.variableHandlerNode.properties.ParameterExtractionNodeProperties;

public class ParameterExtractionNode implements NormalRunnableNode {


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
