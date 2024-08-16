package com.mort.easyllm.workFlow.context;

import java.util.HashMap;

/**
 * 会话变量
 * @Author Mort
 * @Date 2024-08-16
 */
public class SessionVariables {

    private static final ThreadLocal<HashMap<String, String>> SessionVariables = ThreadLocal.withInitial(HashMap::new);

    public static void setSessionVariables(HashMap<String, String> map) {
        SessionVariables.set(map);
    }

    public static HashMap<String, String> getSessionVariables() {
        return SessionVariables.get();
    }

    public static void removeSessionVariables() {
        SessionVariables.remove();
    }

}
