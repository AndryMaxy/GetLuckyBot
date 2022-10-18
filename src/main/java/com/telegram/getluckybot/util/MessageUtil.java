package com.telegram.getluckybot.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageUtil {

    public String toCommand(String data) {
        return data.contains("@") ? data.split("@")[0] : data;
    }
}
