package com.telegram.getluckybot.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CallbackDataUtil {

    public String toCallbackData(String command, String payload) {
        return String.format("%s|%s", command, payload);
    }

    public String toCommand(String data) {
        return split(data)[0];
    }

    public String toPayload(String data) {
        return split(data)[1];
    }

    private String[] split(String data) {
        return data.split("\\|");
    }
}
