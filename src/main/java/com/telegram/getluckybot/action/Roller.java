package com.telegram.getluckybot.action;

import java.util.Random;

public class Roller implements Action {

    private static final int MAX = 101;

    private Random random;

    public Roller() {
        this.random = new Random();
    }

    @Override
    public String act() {
        return String.valueOf(this.random.nextInt(MAX));
    }
}
