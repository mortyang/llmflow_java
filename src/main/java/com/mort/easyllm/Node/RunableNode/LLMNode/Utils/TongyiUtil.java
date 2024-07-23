package com.mort.easyllm.Node.RunableNode.LLMNode.Utils;

import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.mort.easyllm.Config.TongyiConfig;
import com.tongyi.Enums.ModelNameEnums;
import com.tongyi.Normal.FullResponse;

public class TongyiUtil {

    public static FullResponse fullResponseCreator(TongyiConfig tongyiConfig) {
        return FullResponse.builder()
                .apiKeys(tongyiConfig.getApiKey())
                .modelName(tongyiConfig.getModelName())
                .build();
    }

    public static String createFullSession(String input, TongyiConfig tongyiConfig, String... sysMsg) {
        try {
            FullResponse.builder().modelName(tongyiConfig.getModelName()).apiKeys(tongyiConfig.getApiKey()).build();
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
