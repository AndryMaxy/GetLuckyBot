package com.telegram.getluckybot.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class CheerUp implements Action {

    private static final Queue<String> COMPLIMENTS = new LinkedList<>();

    public CheerUp() {
    }

    private void load() {
        var complimentList = new ArrayList<String>();
        complimentList.add("You are incredibly beautiful!");
        complimentList.add("Your smile makes the world brighter.");
        complimentList.add("You have an amazing sense of style.");
        complimentList.add("Your eyes are absolutely mesmerizing.");
        complimentList.add("You have a way of making ordinary moments special.");
        complimentList.add("It is so easy and pleasant to talk to you.");
        complimentList.add("You have an amazing inner light.");
        complimentList.add("You inspire others to be better.");
        complimentList.add("Your kindness makes you even more beautiful.");
        complimentList.add("You always know how to lift peoples spirits.");
        complimentList.add("Your charisma draws people to you.");
        complimentList.add("You have an incredible sense of humor.");
        complimentList.add("You make the world a better place just by being you.");
        complimentList.add("You combine beauty and intelligence in the most perfect way.");
        complimentList.add("Your voice is like music to the soul.");
        complimentList.add("You are incredibly talented.");
        complimentList.add("I want to spend every minute with you.");
        complimentList.add("You are a true muse.");
        complimentList.add("There is a special magic in you.");
        complimentList.add("You are the embodiment of grace and charm.");
        complimentList.add("You look amazing today!");
        complimentList.add("Your smile is absolutely beautiful.");
        complimentList.add("You are so talented!");
        complimentList.add("You are so kind and thoughtful.");
        complimentList.add("You always do an amazing job!");
        complimentList.add("Your hard work really pays off.");
        complimentList.add("You have a brilliant mind!");
        complimentList.add("Your positivity is truly inspiring.");
        complimentList.add("You bring out the best in people.");
        complimentList.add("You are incredibly wise beyond your years.");
        complimentList.add("Your kindness makes the world a better place.");
        complimentList.add("You always know how to make people feel special.");
        complimentList.add("Your energy is contagious.");
        Collections.shuffle(complimentList);
        COMPLIMENTS.addAll(complimentList);
    }

    @Override
    public String act() {
        if (COMPLIMENTS.isEmpty()) {
            load();
        }
        return COMPLIMENTS.poll();
    }
}
