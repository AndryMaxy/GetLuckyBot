package com.telegram.getluckybot;

import com.telegram.getluckybot.handler.Handler;
import java.util.Map;

public record Config(String token, Map<String, Handler> handlerMap) {

}
