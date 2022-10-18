package com.telegram.getluckybot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws TelegramApiException {
        Properties properties = loadProps();
        String token = properties.getProperty("token");
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        TelegramLongPollingBot bot = Configuration.newBot(token);
        api.registerBot(bot);
    }

    private static Properties loadProps() {
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                throw new FileNotFoundException("Sorry, unable to find config.properties");
            }

            prop.load(input);
            return prop;
        } catch (IOException ex) {
            throw new RuntimeException("Cannot load props.", ex);
        }
    }
}
