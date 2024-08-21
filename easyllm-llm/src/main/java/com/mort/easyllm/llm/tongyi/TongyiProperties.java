package com.mort.easyllm.llm.tongyi;


import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import com.mort.easyllm.llm.tongyi.enums.ModelNameEnums;
import lombok.Data;

import java.util.Optional;


@Data
public class TongyiProperties {


    private ModelNameEnums modelName;

    private String apiKey;

    private Float temperature;

    private Integer maxTokens;

    private Double topP;

    private Boolean enableSearch;


    @JSONCreator
    public TongyiProperties(
            @JSONField(name = "modelName") String modelName,
            @JSONField(name = "apiKey") String apiKey,
            @JSONField(name = "temperature") Float temperature,
            @JSONField(name = "maxTokens") Integer maxTokens,
            @JSONField(name = "topP") Double topP,
            @JSONField(name = "enableSearch") Boolean enableSearch) {
        this.modelName = Optional.ofNullable(modelName).map(ModelNameEnums::fromValue).orElse(ModelNameEnums.QWEN_TURBO);
        this.temperature = Optional.ofNullable(temperature).orElse(0.7f);
        this.maxTokens = Optional.ofNullable(maxTokens).orElse(2048);
        this.topP = Optional.ofNullable(topP).orElse(0.95);
        this.enableSearch = Optional.ofNullable(enableSearch).orElse(false);
        this.apiKey = apiKey;
    }


}
