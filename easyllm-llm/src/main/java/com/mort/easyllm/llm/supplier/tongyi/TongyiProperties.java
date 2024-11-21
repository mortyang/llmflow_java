package com.mort.easyllm.llm.supplier.tongyi;


import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import com.mort.easyllm.llm.common.CommonProperties;
import com.mort.easyllm.llm.supplier.tongyi.enums.ModelNameEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.Optional;


@EqualsAndHashCode(callSuper = true)
@Data
public class TongyiProperties extends CommonProperties {


    private ModelNameEnums modelName;

    @NotBlank(message = "apiKey不允许为空")
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
            @JSONField(name = "enableSearch") Boolean enableSearch,
            @JSONField(name = "needSessionContext") Boolean useContext,
            @JSONField(name = "contextLength") Integer contextLength
    ) {
        //TODO：空字符串判断
        this.modelName = Optional.ofNullable(modelName).map(ModelNameEnums::fromValue).orElse(ModelNameEnums.QWEN_TURBO);
        this.temperature = Optional.ofNullable(temperature).orElse(0.7f);
        this.maxTokens = Optional.ofNullable(maxTokens).orElse(2048);
        this.topP = Optional.ofNullable(topP).orElse(0.95);
        this.enableSearch = Optional.ofNullable(enableSearch).orElse(false);
        super.useContext = Optional.ofNullable(useContext).orElse(false);
        super.contextLength = Optional.ofNullable(contextLength).orElse(20);
        this.apiKey = apiKey;
    }


}
