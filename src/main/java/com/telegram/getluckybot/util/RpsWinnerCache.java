package com.telegram.getluckybot.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class RpsWinnerCache {

    private final ConcurrentHashMap<String, Map<String, Integer>> cache;

    private final ScheduledExecutorService scheduledExecutorService;
    private final long delay;

    public RpsWinnerCache(long delay) {
        this.delay = delay;
        this.cache = new ConcurrentHashMap<>();
        scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    }

    public void add(String key, String userName) {
        Map<String, Integer> winners = get(key);
        Integer currentValue = winners.get(userName);
        Integer newValue = currentValue + 1;
        winners.put(userName, newValue);
        cache.put(key, winners);
        scheduledExecutorService.schedule(() -> remove(key), delay, TimeUnit.MINUTES);
    }

    public Map<String, Integer> get(String key) {
        return cache.getOrDefault(key, new Statistics());
    }

    public void remove(String key) {
        cache.remove(key);
    }

    private static class Statistics extends ConcurrentHashMap<String, Integer> {

        @Override
        public Integer get(Object key) {
            Integer value = super.get(key);
            return value != null ? value : 0;
        }
    }
}
