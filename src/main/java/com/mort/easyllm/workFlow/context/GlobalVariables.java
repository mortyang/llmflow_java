package com.mort.easyllm.workFlow.context;

import java.util.HashMap;

/**
 * 单词会话全局变量
 * @Author Mort
 * @Date 2024-08-16
 */
public class GlobalVariables {

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
