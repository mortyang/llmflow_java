package com.mort.easyllm.common.context;


import com.mort.easyllm.common.parameter.Message;
import com.mort.easyllm.common.parameter.SessionData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionContext {


    /**
     * SessionDataThreadLocal
     */

    private static final ThreadLocal<SessionData> SessionDataThreadLocal = new ThreadLocal<>();

    private static final ThreadLocal<HashMap<String, String>> TemporaryVariables = ThreadLocal.withInitial(HashMap::new);


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
     * SessionData getter
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
     * SessionVariables
     */
    public static void putSessionVariable(String text, String nodeName, String... variableName) {
        SessionContext.getSessionDataThreadLocal()
                .getSessionVariables()
                .put(handleVariableName(nodeName, variableName), text);
    }

    public static void putTemporaryVariable(String text, String nodeName, String... variableName) {
        //此处指定了一个node的变量
        SessionContext.TemporaryVariables.get()
                .put(handleVariableName(nodeName, variableName), text);
    }


    public static String getVariableByName(String nodeName, String... variableName) {
        String temp = handleVariableName(nodeName, variableName);
        String text = SessionContext.getSessionVariables().get(temp);
        if (text == null) {
            text = SessionContext.TemporaryVariables.get().get(temp);
        }
        return text;
    }


    public static String getVariableAtLocation(StorageLocation position, String nodeName, String... variableName) {
        String temp = handleVariableName(nodeName, variableName);
        if (position.equals(StorageLocation.SESSION)) {
            return SessionContext.getSessionVariables().get(temp);
        } else if (position.equals(StorageLocation.TEMPORARY)) {
            return SessionContext.TemporaryVariables.get().get(temp);
        }
        throw new IllegalArgumentException("Unknow StorageLocation");
    }

    public enum StorageLocation {
        TEMPORARY,  // 临时存储区域
        SESSION  // 持久化存储区域
    }


    private static String handleVariableName(String nodeName, String... args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("Only one argument is allowed.");
        }
        if (args.length == 1) {
            return nodeName + "-" + args[0];
        }
        return nodeName;
    }

}
