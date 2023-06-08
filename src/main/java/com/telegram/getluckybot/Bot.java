package com.telegram.getluckybot;

import com.telegram.getluckybot.handler.Handler;
import com.telegram.getluckybot.model.RequestMessage;
import java.util.Map;
import java.util.Optional;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    private final Map<String, Handler> handlerMap;
    private final String token;

    public Bot(Config config) {
        this.token = config.token();
        this.handlerMap = config.handlerMap();
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        RequestMessage request;
        if (update.hasCallbackQuery()) {
            request = RequestMessage.of(update.getCallbackQuery());
        } else if (validate(message)) {
            request = RequestMessage.of(message);
        } else {
            return;
        }

        handle(request);
    }

    @Override
    public String getBotUsername() {
        return "GetLuckyBot";
    }

    private void handle(RequestMessage message) {
        Handler handler = handlerMap.get(message.getCommand());
        Optional.ofNullable(handler)
                .or(this::getDefaultHandler)
                .map(it -> it.handle(message))
                .ifPresent(this::send);
    }

    private void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(Message message) {
        return message != null && message.getText() != null && message.getText().startsWith("/");
    }

    private Optional<Handler> getDefaultHandler() {
        return Optional.of(handlerMap.get(Commands.NO_COMMAND.getName()));
    }
}
