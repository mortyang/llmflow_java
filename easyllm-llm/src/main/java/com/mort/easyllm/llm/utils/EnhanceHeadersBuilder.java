package com.mort.easyllm.llm.utils;

import okhttp3.Headers;

public class EnhanceHeadersBuilder {

    private final Headers.Builder builder;

    public EnhanceHeadersBuilder() {
        this.builder = new Headers.Builder();
        this.builder.add("Content-Type", "application/json");
    }

    public EnhanceHeadersBuilder apiKey(String apiKey) {
        this.builder.add("Authorization", "Bearer " + apiKey);
        return this;
    }

    public EnhanceHeadersBuilder sseEnable() {
        this.builder.add("X-DashScope-SSE", "enable");
        return this;
    }

    public EnhanceHeadersBuilder accept(String acceptSort) {
        this.builder.add("Accept", acceptSort);
        return this;
    }

}
