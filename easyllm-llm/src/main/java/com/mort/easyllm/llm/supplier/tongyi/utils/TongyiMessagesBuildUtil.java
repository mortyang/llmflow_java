package com.mort.easyllm.llm.supplier.tongyi.utils;

import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.mort.easyllm.llm.supplier.tongyi.enums.TongyiRole;

import java.util.*;

public class TongyiMessagesBuildUtil {


    public static List<Message> generateMessages(String message, String... sysMsg) {
        List<Message> messageList = new ArrayList<>();
        if (sysMsg.length > 0) {
            if (!Objects.equals(sysMsg[0], "null")) {
                messageList.add(Message.builder()
                        .role(Role.SYSTEM.getValue())
                        .content(Arrays.toString(sysMsg))
                        .build());
            }
        }
        messageList.add(Message.builder()
                .role(Role.USER.getValue())
                .content(message)
                .build());
        return messageList;
    }


    public static List<Message> appendMessages(List<Message> existMessages, String message) {
        int length = existMessages.size();
        if (Objects.equals(existMessages.get(0).getRole(), Role.SYSTEM.getValue())) {
            length -= 1;
        }
        existMessages.add(Message.builder()
                .role(length % 2 == 0 ? Role.USER.getValue() : Role.ASSISTANT.getValue())
                .content(message)
                .build());
        return existMessages;
    }


    public static List<Message> messageAdapter(List<com.mort.easyllm.common.parameter.Message> messages) {
        List<Message> tongyiMessage = new ArrayList<>();
        messages.forEach((message) -> {
            tongyiMessage.add(Message.builder()
                    .role(TongyiRole.fromCommonRole(message.getRole().getRoleName()).getTongyiRole())
                    .content(message.getText())
                    .build());
        });
        return tongyiMessage;
    }


}
