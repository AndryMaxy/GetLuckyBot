package com.telegram.getluckybot.handler;

import com.telegram.getluckybot.model.RequestMessage;
import com.telegram.getluckybot.util.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class NoCommandHandler implements Handler {

    @Override
    public SendMessage handle(RequestMessage message) {
        String text = """
                There is no command %s\\.
                Known commands:
                /roll \\- generates a number from 0 to 100
                /coin \\- heads or tails
                /rps  \\- rock\\-paper\\-scissors""";
        String response = String.format(text, message.getCommand());
        return SendMessageUtil.of(message.getChatId(), response);
    }
}
