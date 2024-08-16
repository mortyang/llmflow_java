package com.mort.easyllm.workFlow.exception.running;

public class nodeRunningException extends RuntimeException{

    private int id;

    private String nodeName;

    private String workFlowName;



    /**
     * Constructs a new runtime exception with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     * @since 1.7
     */
    public nodeRunningException(String message, Throwable cause, int id, String workFlowName, String nodeName) {
        super(message, cause);
        this.id = id;
        this.workFlowName = workFlowName;
        this.nodeName = nodeName;
    }
}
