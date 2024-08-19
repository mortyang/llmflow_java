package com.mort.easyllm.workflow.exception.building;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class nodeBuildException extends RuntimeException {

    private final String nodeName;

    private final String workFlowName;

    public nodeBuildException(String message, Throwable cause, String nodeName) {
        super(message, cause);
        this.nodeName = nodeName;
        this.workFlowName = null;
    }

    public nodeBuildException(String message, Throwable cause, String nodeName, String workFlowName) {
        super(message, cause);
        this.nodeName = nodeName;
        this.workFlowName = workFlowName;
    }

}
