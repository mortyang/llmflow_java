package com.mort.easyllm.common.config;

import com.tongyi.Enums.ModelNameEnums;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mort
 */
@Getter
@Setter
public class TongyiConfig {

    public static final String DEFAULT_API_KEY = "sk-40a4dab15f134fa0b2c5a4c7a7a44a1b";

    public static final ModelNameEnums DEFAULT_MODEL_NAME = ModelNameEnums.QWEN_TURBO;


    private String apiKey;

    private ModelNameEnums modelName;


    public TongyiConfig(String modelName) {
        if (modelName != null) {
            try {
                this.modelName = ModelNameEnums.valueOf(modelName);
            } catch (IllegalArgumentException ignore) {
                this.modelName = DEFAULT_MODEL_NAME;
//                throw new RuntimeException("不存在的模型类型");
            }
        } else {
            this.modelName = DEFAULT_MODEL_NAME;
        }
        this.apiKey = DEFAULT_API_KEY;
    }

}