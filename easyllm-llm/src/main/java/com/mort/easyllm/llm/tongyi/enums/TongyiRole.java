package com.mort.easyllm.llm.tongyi.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum TongyiRole {

    /**
     * The human side.
     */
    USER("user", "user"),
    ASSISTANT("assistant", "assistant"),
    /**
     * The model side.
     */
    SYSTEM("system", "system"),
    ATTACHMENT("attachment", "attachment"),
    ;

    private final String commonRole;
    private final String tongyiRole;

    TongyiRole(String commonRole, String tongyiRole) {
        this.commonRole = commonRole;
        this.tongyiRole = tongyiRole;
    }


    private static final Map<String, TongyiRole> COMMON_ROLE_MAP = new HashMap<>();


    static {
        for (TongyiRole role : TongyiRole.values()) {
            COMMON_ROLE_MAP.put(role.getCommonRole(), role);
        }
    }


    public static TongyiRole fromCommonRole(String commonRole) {
        TongyiRole role = COMMON_ROLE_MAP.get(commonRole);
        if (role == null) {
            throw new IllegalArgumentException("No enum constant for commonRole: " + commonRole);
        }
        return role;
    }
}
