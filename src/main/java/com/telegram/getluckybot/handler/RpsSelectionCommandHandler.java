package com.telegram.getluckybot.handler;

import com.telegram.getluckybot.model.RequestMessage;
import com.telegram.getluckybot.model.RpsType;
import com.telegram.getluckybot.model.RpsUserSelection;
import com.telegram.getluckybot.util.RpsUserSelectionCache;
import com.telegram.getluckybot.util.SendMessageUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.*;

public class RpsSelectionCommandHandler implements Handler {

    @Override
    public SendMessage handle(RequestMessage message) {
        String response = getResponse(message);
        return SendMessageUtil.of(message.getChatId(), response);
    }

    private synchronized String getResponse(RequestMessage message) {
        String chatId = message.getChatId();
        List<RpsUserSelection> selections = RpsUserSelectionCache.get(chatId);

        boolean alreadySelected = selections.stream()
                .map(s -> s.getUser().getId())
                .anyMatch(id -> message.getFrom().getId().equals(id));

        if (alreadySelected) {
            return "Wait for an opponent\\!";
        }

        if (selections.size() < 2) {
            RpsType rpsType = RpsType.valueOf(message.getPayload());
            selections.add(new RpsUserSelection(message.getFrom(), rpsType));
            RpsUserSelectionCache.put(chatId, selections);
        }

        if (selections.size() == 2) {
            String resultText = vs(selections);
            RpsUserSelectionCache.remove(message.getChatId());
            return resultText;
        }

        if (selections.size() > 2) {
            RpsUserSelectionCache.remove(message.getChatId());
            return "There is a bug so you all lose\\! Try again\\.";
        }

        return String.format("%s waits for an opponent\\!", getName(message.getFrom()));
    }

    private String vs(List<RpsUserSelection> selections) {
        RpsUserSelection s1 = selections.get(0);
        RpsUserSelection s2 = selections.get(1);

        String resultText;
        RpsType rpsType = s1.getRpsType();
        int result = s1.getRpsType().vs(s2.getRpsType());
        if (result == 1) {
            resultText = getWinner(s1);
        } else if (result == -1) {
            resultText = getWinner(s2);
        } else {
            resultText = String.format("It is draw\\. You both selected %s\\.", rpsType.name());
        }
        return resultText;
    }

    private String getWinner(RpsUserSelection winner) {
        return String.format("%s won with %s\\!", getName(winner.getUser()), winner.getRpsType().name());
    }

    private String getName(User user) {
        return user.getUserName() == null ? user.getFirstName() : user.getUserName();
    }
}
