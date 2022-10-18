package com.telegram.getluckybot.handler;

import com.telegram.getluckybot.model.RequestMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Handler {

    SendMessage handle(RequestMessage message);
}
