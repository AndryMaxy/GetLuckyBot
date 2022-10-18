package com.telegram.getluckybot.action;

import java.util.Random;

public class CoinRoller implements Action {

    private static final String[] SIDES = new String[] {"Heads", "Tails"};
    private static final int MAX = 2;

    private Random random;

    public CoinRoller() {
        this.random = new Random();
    }

    @Override
    public String act() {
        return SIDES[this.random.nextInt(MAX)];
    }
}
