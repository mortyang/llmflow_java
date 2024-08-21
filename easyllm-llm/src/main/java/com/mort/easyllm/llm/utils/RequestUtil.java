package com.mort.easyllm.llm.utils;

import okhttp3.Headers;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RequestUtil {


    public Request generateReq() {
        HashMap<String,String> hashMap = new HashMap<>();
        return new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .headers(new Headers.Builder()
                        .build())
                .build();
    }


}
