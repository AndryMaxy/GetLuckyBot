package com.telegram.getluckybot;

import lombok.Getter;

@Getter
public enum Commands {
    COIN("coin", "/coin", Type.USER, "heads or tails"),
    ROLL("roll", "/roll", Type.USER,"generates a number from 0 to 100"),
    RPS("rps", "/rps", Type.USER, "rock-paper-scissors"),
    HELP("help", "/help", Type.USER, "list of commands"),
    RPS_SELECTED("rps-selected", "/rps-selected", Type.SYSTEM, "system command"),
    NO_COMMAND("no-command", "/", Type.SYSTEM, "system command");

    private final String name;
    private final String address;
    private final Type type;
    private final String description;

    Commands(String name, String address, Type type, String description) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", address, description);
    }

    public enum Type {
        SYSTEM, USER
    }
}
