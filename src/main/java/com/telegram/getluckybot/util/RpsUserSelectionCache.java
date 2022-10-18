package com.telegram.getluckybot.util;

import com.telegram.getluckybot.model.RpsUserSelection;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public final class RpsUserSelectionCache {

    private static final ConcurrentHashMap<String, List<RpsUserSelection>> CACHE = new ConcurrentHashMap<>();

    public static void put(String key, List<RpsUserSelection> selections) {
        CACHE.put(key, selections);
    }

    public static List<RpsUserSelection> get(String key) {
        return CACHE.getOrDefault(key, new ArrayList<>());
    }

    public static void remove(String key) {
        CACHE.remove(key);
    }
}
