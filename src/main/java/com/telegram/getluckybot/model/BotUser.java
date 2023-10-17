package com.telegram.getluckybot.model;

import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.User;

public record BotUser(User user) {

  @NonNull
  public Long getId() {
    return user.getId();
  }

  @NonNull
  public String getName() {
    return user.getUserName() == null ? user.getFirstName() : user.getUserName();
  }
}
