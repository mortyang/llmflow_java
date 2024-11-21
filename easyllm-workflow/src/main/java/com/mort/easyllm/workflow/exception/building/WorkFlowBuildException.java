package com.mort.easyllm.workflow.exception.building;

import lombok.Getter;

@Getter
public class WorkFlowBuildException extends RuntimeException {

    private final String nodeName;

    private final String workFlowName;

    public WorkFlowBuildException(String message, Throwable cause, String nodeName) {
        super(message, cause);
        this.nodeName = nodeName;
        this.workFlowName = null;
    }

    public WorkFlowBuildException(String message, Throwable cause, String nodeName, String workFlowName) {
        super(message, cause);
        this.nodeName = nodeName;
        this.workFlowName = workFlowName;
    }

}
