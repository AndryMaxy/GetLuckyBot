package com.telegram.getluckybot.handler;

import com.telegram.getluckybot.Commands;
import com.telegram.getluckybot.model.RequestMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class HelpCommandHandler implements Handler {

    @Override
    public SendMessage handle(RequestMessage message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Look at the commands\\.");
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyMarkup(getSettingsKeyboard());
        sendMessage.enableMarkdownV2(true);

        sendMessage.setReplyMarkup(getSettingsKeyboard());
        return sendMessage;
    }

    private ReplyKeyboardMarkup getSettingsKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(Commands.COIN.getAddress());
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(Commands.ROLL.getAddress());
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(Commands.RPS.getAddress());
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}
