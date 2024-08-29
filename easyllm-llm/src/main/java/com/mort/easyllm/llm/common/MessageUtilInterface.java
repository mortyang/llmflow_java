package com.mort.easyllm.llm.common;

import com.mort.easyllm.common.parameter.Message;

import java.util.List;

public interface MessageUtilInterface<T> {

    List<T> messageAdapter(List<Message> messages);

}
