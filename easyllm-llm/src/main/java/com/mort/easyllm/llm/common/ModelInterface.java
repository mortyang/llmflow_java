package com.mort.easyllm.llm.common;

import com.mort.easyllm.common.parameter.Message;
import io.reactivex.functions.Consumer;

import java.util.List;

public interface ModelInterface<T> {

    String simpleSession(T properties, String message);

    String fullSession(T properties,List<Message> messages);

    String streamSession(T properties,List<Message> messages, Consumer<String> callback);

}
