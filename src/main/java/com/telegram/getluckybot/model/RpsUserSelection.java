package com.telegram.getluckybot.model;

import org.telegram.telegrambots.meta.api.objects.User;

public record RpsUserSelection(User user, RpsType rpsType) {

}
