package com.telegram.getluckybot;

import lombok.Getter;

@Getter
public enum Commands {
    COIN ("coin", "/coin"),
    ROLL("roll", "/roll"),
    RPS("rps", "/rps"),
    RPS_SELECTED("rps-selected", "/rps-selected"),
    HELP("help", "/help"),
    NO_COMMAND("no_command", "/");

    private final String name;
    private final String address;

    Commands(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
