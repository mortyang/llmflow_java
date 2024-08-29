package com.mort.easyllm.main.pojo.vo;

import com.mort.easyllm.common.parameter.SessionData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseVo {

    private String text;

    private String sessionId;

    private SessionData sessionData;

}
