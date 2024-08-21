package com.mort.easyllm.llm.tongyi.impl;

import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.mort.easyllm.common.parameter.Message;
import com.mort.easyllm.llm.general.LLMNodeInterface;
import com.mort.easyllm.llm.tongyi.TongyiProperties;
import com.mort.easyllm.llm.tongyi.utils.GenerationService;
import com.mort.easyllm.llm.tongyi.utils.MessagesBuildUtil;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class Tongyi implements LLMNodeInterface<TongyiProperties> {


    private GenerationParam buildGenerationParam(TongyiProperties properties, List<com.alibaba.dashscope.common.Message> messages, boolean isStream) {
        log.info("request message:{}", messages.toString());
        return GenerationParam.builder()
                .messages(messages)
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .incrementalOutput(isStream)
                .model(properties.getModelName().toString())
                .apiKey(properties.getApiKey())
                .temperature(properties.getTemperature())
                .maxTokens(properties.getMaxTokens())
                .topP(properties.getTopP())
                .enableSearch(properties.getEnableSearch())
                .build();
    }

    public String simpleSession(TongyiProperties properties, String message) {
        try {
            List<com.alibaba.dashscope.common.Message> messages = MessagesBuildUtil.generateMessages(message);
            GenerationParam param = buildGenerationParam(properties, messages, false);
            GenerationResult result = GenerationService.getGeneration().call(param);
            return result.getOutput().getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            throw new RuntimeException("大模型请求异常", e);
        }
    }

    public String fullSession(TongyiProperties properties, List<Message> messages) {
        try {
            GenerationParam param = buildGenerationParam(properties, MessagesBuildUtil.toTongyiMessageConverter(messages), false);
            GenerationResult result = GenerationService.getGeneration().call(param);
            return result.getOutput().getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            throw new RuntimeException("大模型请求异常", e);
        }
    }

    public String streamSession(TongyiProperties properties, List<Message> messages, Consumer<String> callback) {
        try {
            GenerationParam param = buildGenerationParam(properties, MessagesBuildUtil.toTongyiMessageConverter(messages), true);
            Flowable<GenerationResult> result = GenerationService.getGeneration().streamCall(param);
            StringBuilder fullContent = new StringBuilder();
            result.blockingForEach(message -> {
                callback.accept(message.getOutput().getChoices().get(0).getMessage().getContent());
                fullContent.append(message.getOutput().getChoices().get(0).getMessage().getContent());
            });
            return fullContent.toString();
        } catch (Exception e) {
            throw new RuntimeException("大模型请求异常", e);
        }
    }

}
