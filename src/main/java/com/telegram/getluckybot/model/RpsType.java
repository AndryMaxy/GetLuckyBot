package com.telegram.getluckybot.model;

public enum RpsType {
    ROCK("SCISSORS"), SCISSORS("PAPER"), PAPER("ROCK");

    private String loser;

    RpsType(String loser) {
        this.loser = loser;
    }

    public int vs(RpsType type) {
        if (this == type) {
            return 0;
        }
        if (RpsType.valueOf(this.loser) == type) {
            return 1;
        }
        return -1;
    }
}
