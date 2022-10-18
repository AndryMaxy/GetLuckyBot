package com.telegram.getluckybot.util;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@UtilityClass
public class SendMessageUtil {

    public SendMessage of(String chatId, String response) {
        SendMessage message = new SendMessage(chatId, response);
        message.enableMarkdownV2(true);
        return message;
    }
}
