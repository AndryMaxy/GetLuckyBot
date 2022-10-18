package com.telegram.getluckybot.model;

import com.telegram.getluckybot.util.CallbackDataUtil;
import com.telegram.getluckybot.util.MessageUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
@Accessors(chain = true)
public class RequestMessage {

    private String chatId;
    private String command;
    private String payload;
    private User from;

    public static RequestMessage of(Message message) {
        return new RequestMessage()
                .setChatId(message.getChatId().toString())
                .setCommand(MessageUtil.toCommand(message.getText()))
                .setFrom(message.getFrom());
    }

    public static RequestMessage of(CallbackQuery query) {
        return new RequestMessage()
                .setChatId(query.getMessage().getChatId().toString())
                .setCommand(CallbackDataUtil.toCommand(query.getData()))
                .setPayload(CallbackDataUtil.toPayload(query.getData()))
                .setFrom(query.getFrom());
    }
}
