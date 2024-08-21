package com.mort.easyllm.workflow.context;

import java.util.HashMap;

/**
 * 会话全局变量
 * @Author Mort
 * @Date 2024-08-16
 */
public class GlobalRunningVariables {

    private static final ThreadLocal<HashMap<String, String>> GlobalVariables = ThreadLocal.withInitial(HashMap::new);

    public static void setGlobalVariables(HashMap<String, String> map) {
        GlobalVariables.set(map);
    }

    public static HashMap<String, String> getGlobalVariables() {
        return GlobalVariables.get();
    }

    public static void removeGlobalVariables() {
        GlobalVariables.remove();
    }

}
