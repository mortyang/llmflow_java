package com.mort.easyllm.llmModel;

import com.mort.easyllm.workFlow.parameter.Message;

import java.util.List;

public interface LLMModel {


    List<Message> singleFullSession();

    List<Message> multiFullSession();

    List<Message> singleStreamSession();

    List<Message> multiStreamSession();

}
