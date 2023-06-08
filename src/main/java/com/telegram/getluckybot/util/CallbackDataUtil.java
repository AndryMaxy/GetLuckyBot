package com.telegram.getluckybot.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class CallbackDataUtil {

    public static String toCallbackData(String command, String payload) {
        return String.format("%s|%s", command, payload);
    }

    public static String toCommand(String data) {
        return split(data)[0];
    }

    public static String toPayload(String data) {
        return split(data)[1];
    }

    private static String[] split(String data) {
        return data.split("\\|");
    }
}
