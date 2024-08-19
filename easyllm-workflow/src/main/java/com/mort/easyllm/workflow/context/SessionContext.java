package com.mort.easyllm.workflow.context;

import com.mort.easyllm.workflow.parameter.Message;

import java.util.ArrayList;
import java.util.List;

public class SessionContext {

    private static final ThreadLocal<List<Message>> SessionContext = ThreadLocal.withInitial(ArrayList::new);

    public static void setSessionContext(List<Message> messages) {
        SessionContext.set(messages);
    }

    public static List<Message> getSessionContext() {
        return SessionContext.get();
    }

    public static void removeSessionContext() {
        SessionContext.remove();
    }
}
