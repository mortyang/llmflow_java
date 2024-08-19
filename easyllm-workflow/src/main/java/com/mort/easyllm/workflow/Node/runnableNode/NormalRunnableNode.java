package com.mort.easyllm.workflow.Node.runnableNode;

public interface NormalRunnableNode {


    /**
     * @param input 默认输入
     * @return output
     */
    String run(String input);


    default String run(){
        return null;
    };

}
