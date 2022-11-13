package com.telegram.getluckybot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) throws TelegramApiException {
        String token = System.getenv().get("token");;
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        TelegramLongPollingBot bot = Configuration.newBot(token);
        api.registerBot(bot);
    }
}
