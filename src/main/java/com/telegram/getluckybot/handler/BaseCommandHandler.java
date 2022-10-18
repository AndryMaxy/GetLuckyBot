package com.telegram.getluckybot.handler;

import com.telegram.getluckybot.action.Action;
import com.telegram.getluckybot.model.RequestMessage;
import com.telegram.getluckybot.util.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class BaseCommandHandler implements Handler {

    private Action action;

    public BaseCommandHandler(Action action) {
        this.action = action;
    }

    @Override
    public SendMessage handle(RequestMessage message) {
        String response = action.act();
        return SendMessageUtil.of(message.getChatId(), response);
    }
}
