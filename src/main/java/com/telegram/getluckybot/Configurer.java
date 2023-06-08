package com.telegram.getluckybot;

import com.telegram.getluckybot.action.Action;
import com.telegram.getluckybot.action.CoinRoller;
import com.telegram.getluckybot.action.Roller;
import com.telegram.getluckybot.handler.BaseCommandHandler;
import com.telegram.getluckybot.handler.Handler;
import com.telegram.getluckybot.handler.NoCommandHandler;
import com.telegram.getluckybot.handler.RpsCommandHandler;
import com.telegram.getluckybot.handler.RpsSelectionCommandHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Configurer {

    private final String token;

    public Configurer(String token) {
        this.token = token;
    }

    public Config configure() {
        Map<String, Handler> handlerMap = new HashMap<>();
        handlerMap.put(Commands.COIN.getAddress(), newCoinCommandHandler());
        handlerMap.put(Commands.ROLL.getAddress(), newRollCommandHandler());
        handlerMap.put(Commands.RPS.getAddress(), newRpsCommandHandler());
        handlerMap.put(Commands.HELP.getAddress(), newHelpCommandHandler());
        handlerMap.put(Commands.NO_COMMAND.getName(), newNoCommandHandler());
        handlerMap.put(Commands.RPS_SELECTED.getAddress(), newRpsSelectionCommandHandler());
        return new Config(token, handlerMap);
    }

    private final String userCommands = getUserCommandsDescriptionText();

    private Action newCoinRollerAction() {
        return new CoinRoller();
    }

    private Action newRollerAction() {
        return new Roller();
    }

    private Handler newCoinCommandHandler() {
        return new BaseCommandHandler(newCoinRollerAction());
    }

    private Handler newRollCommandHandler() {
        return new BaseCommandHandler(newRollerAction());
    }

    private Handler newHelpCommandHandler() {
        return new BaseCommandHandler(() -> "Look at the commands:\n" + userCommands);
    }

    private Handler newRpsCommandHandler() {
        return new RpsCommandHandler();
    }

    private Handler newRpsSelectionCommandHandler() {
        return new RpsSelectionCommandHandler();
    }

    private Handler newNoCommandHandler() {
        return new NoCommandHandler(() -> userCommands);
    }

    private String getUserCommandsDescriptionText() {
        return Arrays.stream(Commands.values())
                .filter(command -> command.getType() == Commands.Type.USER)
                .map(Commands::toString)
                .reduce("", (acc, v) -> acc + v + "\n");
    }
}
