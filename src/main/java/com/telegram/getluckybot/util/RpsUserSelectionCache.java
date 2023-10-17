package com.telegram.getluckybot.util;

import com.telegram.getluckybot.model.RpsUserSelection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class RpsUserSelectionCache {

    private final ConcurrentHashMap<String, List<RpsUserSelection>> cache;

    public RpsUserSelectionCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public void put(String key, List<RpsUserSelection> selections) {
        cache.put(key, selections);
    }

    public List<RpsUserSelection> get(String key) {
        return cache.getOrDefault(key, new ArrayList<>());
    }

    public void remove(String key) {
        cache.remove(key);
    }
}
