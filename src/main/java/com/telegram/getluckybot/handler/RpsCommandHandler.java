package com.telegram.getluckybot.handler;

import com.telegram.getluckybot.Commands;
import com.telegram.getluckybot.model.RequestMessage;
import com.telegram.getluckybot.model.RpsType;
import com.telegram.getluckybot.util.CallbackDataUtil;
import com.telegram.getluckybot.util.RpsUserSelectionCache;
import com.telegram.getluckybot.util.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class RpsCommandHandler implements Handler {

    @Override
    public SendMessage handle(RequestMessage message) {
        clearCacheForChat(message.getChatId());

        SendMessage sendMessage = SendMessageUtil.of(message.getChatId(), "Select a weapon!");
        sendMessage.setReplyMarkup(getSettingsInlineKeyboard());
        return sendMessage;
    }

    private InlineKeyboardMarkup getSettingsInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton rock = new InlineKeyboardButton();
        rock.setText(RpsType.ROCK.name());
        rock.setCallbackData(toData(RpsType.ROCK));

        InlineKeyboardButton paper = new InlineKeyboardButton();
        paper.setText(RpsType.PAPER.name());
        paper.setCallbackData(toData(RpsType.PAPER));

        InlineKeyboardButton scissors = new InlineKeyboardButton();
        scissors.setText(RpsType.SCISSORS.name());
        scissors.setCallbackData(toData(RpsType.SCISSORS));

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(rock);
        row1.add(paper);
        row1.add(scissors);

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(row1);
        inlineKeyboardMarkup.setKeyboard(buttons);
        return inlineKeyboardMarkup;
    }

    private String toData(RpsType type) {
        return CallbackDataUtil.toCallbackData(Commands.RPS_SELECTED.getAddress(), type.name());
    }

    private void clearCacheForChat(String chatId) {
        RpsUserSelectionCache.remove(chatId);
    }
}
