package com.telegram.getluckybot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) throws TelegramApiException {
        String token = getToken(args);
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        Configurer configurer = new Configurer(token);
        Config config = configurer.configure();
        Bot bot = new Bot(config);
        api.registerBot(bot);
    }

    private static String getToken(String[] args) {
        Properties properties = loadProps();
        String token = properties.getProperty("token");
        if (token != null) {
            return token;
        }
        if (args.length > 0) {
            return args[0];
        }
        throw new RuntimeException("Provide token.");
    }

    private static Properties loadProps() {
        Properties prop = new Properties();

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                throw new FileNotFoundException("Sorry, unable to find config.properties");
            }
            prop.load(input);
        } catch (IOException ex) {
            System.out.println("Cannot load props.");
        }

        return prop;
    }
}
