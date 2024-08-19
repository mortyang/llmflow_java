package com.mort.easyllm.workFlow.Node.runnableNode;

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
