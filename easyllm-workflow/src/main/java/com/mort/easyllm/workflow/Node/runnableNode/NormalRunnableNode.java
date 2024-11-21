package com.mort.easyllm.workflow.Node.runnableNode;

import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import io.reactivex.functions.Consumer;

public interface NormalRunnableNode {


    /**
     * @return output
     */
    String run(InfoNode infoNode, Consumer<String> callback);


}
