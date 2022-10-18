package com.telegram.getluckybot;

import com.telegram.getluckybot.action.Action;
import com.telegram.getluckybot.action.CoinRoller;
import com.telegram.getluckybot.action.Roller;
import com.telegram.getluckybot.handler.*;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public final class Configuration {

    public static TelegramLongPollingBot newBot(String token) {
        Map<String, Handler> handlerMap = new HashMap<>();
        handlerMap.put(Commands.COIN.getAddress(), newCoinCommandHandler());
        handlerMap.put(Commands.ROLL.getAddress(), newRollCommandHandler());
        handlerMap.put(Commands.RPS.getAddress(), newRpsCommandHandler());
        handlerMap.put(Commands.RPS_SELECTED.getAddress(), newRpsSelectionCommandHandler());
        handlerMap.put(Commands.NO_COMMAND.getName(), newNoCommandHandler());
//        handlerMap.put(Commands.HELP.getAddress(), newNoCommandHandler());
        return new Bot(token, handlerMap);
    }

    private static Action newCoinRollerAction() {
        return new CoinRoller();
    }

    private static Action newRollerAction() {
        return new Roller();
    }

    private static Handler newCoinCommandHandler() {
        return new BaseCommandHandler(newCoinRollerAction());
    }

    private static Handler newRollCommandHandler() {
        return new BaseCommandHandler(newRollerAction());
    }

    private static Handler newHelpCommandHandler() {
        return new HelpCommandHandler();
    }

    private static Handler newRpsCommandHandler() {
        return new RpsCommandHandler();
    }

    private static Handler newRpsSelectionCommandHandler() {
        return new RpsSelectionCommandHandler();
    }

    private static Handler newNoCommandHandler() {
        return new NoCommandHandler();
    }
}
