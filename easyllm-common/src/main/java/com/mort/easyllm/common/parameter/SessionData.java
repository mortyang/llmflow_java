package com.mort.easyllm.common.parameter;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class SessionData {

    @NonNull
    private String sessionId;

    @NonNull
    private List<Message> historyMessages;

    @NonNull
    private Map<String, String> sessionVariables;


}
