package com.mort.easyllm.workflow.parameter;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Message {

    private final Role role;

    private final String text;

    @Getter
    public enum Role {
        USER("user"),
        ASSIST("assist"),
        SYSTEM("system");

        private final String value;

        Role(String value) {
            this.value = value;
        }
    }

    @Builder
    public Message(String role, String text) {
        this.text = text;
        this.role = Role.valueOf(role);
    }

}
