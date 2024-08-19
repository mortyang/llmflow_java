package com.mort.easyllm.llmModel.impl;

import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.mort.easyllm.config.TongyiConfig;
import com.mort.easyllm.workFlow.Node.runnableNode.llmNode.utils.TongyiUtil;
import com.tongyi.Normal.FullResponse;

public class AliTongYi {


    public static FullResponse fullResponseCreator(TongyiConfig tongyiConfig) {
        return FullResponse.builder()
                .apiKeys(tongyiConfig.getApiKey())
                .modelName(tongyiConfig.getModelName())
                .build();
    }

    public static String createFullSession(String input, TongyiConfig tongyiConfig, String... sysMsg) {
        try {
            FullResponse.builder()
                    .modelName(tongyiConfig.getModelName())
                    .apiKeys(tongyiConfig.getApiKey())
                    .build();
            return TongyiUtil.fullResponseCreator(tongyiConfig).singleSession(input, sysMsg);
        } catch (NullPointerException e) {
            throw new RuntimeException("LLM parameter absent");
        } catch (NoApiKeyException e) {
            throw new RuntimeException("tongyi apikey not config");
        } catch (InputRequiredException e) {
            throw new RuntimeException("Input was required when call LLM");
        } catch (Exception e) {
            throw new RuntimeException("Errors occured when create LLM request");
        }
    }


}
