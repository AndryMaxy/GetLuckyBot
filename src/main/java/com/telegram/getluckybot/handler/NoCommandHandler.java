package com.telegram.getluckybot.handler;

import com.telegram.getluckybot.action.Action;
import com.telegram.getluckybot.model.RequestMessage;
import com.telegram.getluckybot.util.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class NoCommandHandler implements Handler {

    private final Action action;

    public NoCommandHandler(Action action) {
        this.action = action;
    }

    @Override
    public SendMessage handle(RequestMessage message) {
        String commands = action.act();
        String text = """
                There is no command %s.
                Known commands:
                %s""";
        String response = String.format(text, message.getCommand(), commands);
        return SendMessageUtil.of(message.getChatId(), response);
    }
}
