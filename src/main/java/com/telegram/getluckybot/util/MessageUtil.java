package com.telegram.getluckybot.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class MessageUtil {

    public static String toCommand(String data) {
        return data.contains("@") ? data.split("@")[0] : data;
    }
}
