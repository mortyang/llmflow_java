package com.mort.easyllm.common.context;


import com.mort.easyllm.common.parameter.Message;
import com.mort.easyllm.common.parameter.SessionData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionContext {

    private static final ThreadLocal<SessionData> SessionDataThreadLocal = new ThreadLocal<>();

    private static final ThreadLocal<HashMap<String, String>> TemporaryVariables = ThreadLocal.withInitial(HashMap::new);


    /**
     * basic-SessionDataThreadLocal
     */
    public static SessionData getSessionDataThreadLocal() {
        return SessionDataThreadLocal.get();
    }

    public static void setSessionDataThreadLocal(SessionData sessionData) {
        SessionDataThreadLocal.set(sessionData);
    }

    public static void removeSessionContext() {
        SessionDataThreadLocal.remove();
        TemporaryVariables.remove();
    }


    /**
     * getter
     */
    public static List<Message> getHistoryMessages() {
        return SessionContext.SessionDataThreadLocal.get().getHistoryMessages();
    }

    public static String getSessionId() {
        return SessionContext.SessionDataThreadLocal.get().getSessionId();
    }

    public static Map<String, String> getSessionVariables() {
        return SessionContext.SessionDataThreadLocal.get().getSessionVariables();
    }


    /**
     * Intent
     */
    public static void setLatestIntent(String intent) {
        SessionDataThreadLocal.get().getSessionVariables().put("latestIntent", intent);
        SessionContext.getSessionDataThreadLocal().setLatestIntent(intent);
    }

    public static String getLatestIntent() {
        String intent = SessionDataThreadLocal.get().getSessionVariables().get("latestIntent");
        if (intent == null) {
            intent = SessionContext.getSessionDataThreadLocal().getLatestIntent();
        }
        return intent;
    }


    /**
     * Variables
     */
    public static void putSessionVariable(String nodeName, String variableName, String text) {
        if (variableName == null) {
            SessionContext.TemporaryVariables.get()
                    .put(nodeName, text);
            return;
        } else if (variableName.isEmpty()) {
            SessionContext.TemporaryVariables.get()
                    .put(nodeName, text);
            return;
        }
        SessionContext.getSessionDataThreadLocal()
                .getSessionVariables()
                .put(nodeName + "-" + variableName, text);
    }

    public static void putGlobalVariable(String nodeName, String variableName, String text) {
        if (variableName == null) {
            SessionContext.TemporaryVariables.get()
                    .put(nodeName, text);
            return;
        } else if (variableName.isEmpty()) {
            SessionContext.TemporaryVariables.get()
                    .put(nodeName, text);
            return;
        }
        SessionContext.TemporaryVariables.get()
                .put(nodeName + "-" + variableName, text);
    }


    public static String getVariable(String variableName) {
        String text = SessionContext.getSessionVariables().get(variableName);
        if (text == null) {
            text = SessionContext.TemporaryVariables.get().get(variableName);
        }
        return text;
    }


    public static String getVariableAtLocation(String variableName, StorageLocation position) {
        String text = null;
        if (position.equals(StorageLocation.SESSION)) {
            text = SessionContext.getSessionVariables().get(variableName);
        } else if (position.equals(StorageLocation.TEMPORARY)) {
            text = SessionContext.TemporaryVariables.get().get(variableName);
        }
        return text;
    }

    public enum StorageLocation {
        TEMPORARY,  // 临时存储区域
        SESSION  // 持久化存储区域
    }


}
