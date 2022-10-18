package com.telegram.getluckybot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
@RequiredArgsConstructor
public class RpsUserSelection {

    private final User user;
    private final RpsType rpsType;
}
