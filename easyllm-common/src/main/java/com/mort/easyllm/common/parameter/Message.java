package com.mort.easyllm.common.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Message {

    private final Role role;

    private final String text;

    @Getter
    public enum Role {

        user("user"),
        assist("assist"),
        system("system");

        private final String roleName;

        Role(String roleName) {
            this.roleName = roleName;
        }
    }

    @Builder
    @JsonCreator
    public Message(@JsonProperty("role") String role, @JsonProperty("text") String text) {
        this.text = text;
        this.role = Role.valueOf(role);
    }

}
