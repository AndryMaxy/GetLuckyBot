package com.telegram.getluckybot.handler;

import com.telegram.getluckybot.model.BotUser;
import com.telegram.getluckybot.model.RequestMessage;
import com.telegram.getluckybot.model.RpsType;
import com.telegram.getluckybot.model.RpsUserSelection;
import com.telegram.getluckybot.util.RpsUserSelectionCache;
import com.telegram.getluckybot.util.RpsWinnerCache;
import com.telegram.getluckybot.util.SendMessageUtil;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RpsSelectionCommandHandler implements Handler {

    private final RpsUserSelectionCache selectionCache;
    private final RpsGame rpsGame;

    public RpsSelectionCommandHandler(
        RpsUserSelectionCache selectionCache,
        RpsWinnerCache winnerCache
    ) {
        this.selectionCache = selectionCache;
        this.rpsGame = new RpsGame(selectionCache, winnerCache);
    }

    @Override
    public SendMessage handle(RequestMessage message) {
        String response = getResponse(message);
        return SendMessageUtil.of(message.getChatId(), response);
    }

    private synchronized String getResponse(RequestMessage message) {
        String chatId = message.getChatId();
        BotUser user = new BotUser(message.getFrom());
        List<RpsUserSelection> selections = selectionCache.get(chatId);

        boolean alreadySelected = selections.stream()
                .map(s -> s.user().getId())
                .anyMatch(id -> user.getId().equals(id));

        if (alreadySelected) {
            return "Wait for an opponent!";
        }

        if (selections.size() < 2) {
            RpsType rpsType = RpsType.valueOf(message.getPayload());
            selections.add(new RpsUserSelection(user, rpsType));
            selectionCache.put(chatId, selections);
        }

        if (selections.size() == 2) {
            return rpsGame.play(chatId);
        }

        if (selections.size() > 2) {
            selectionCache.remove(message.getChatId());
            return "There is a bug so you all lose! Try again.";
        }

        return "%s waits for an opponent!".formatted(user.getName());
    }

    private static class RpsGame {
        private final RpsUserSelectionCache selectionCache;
        private final RpsWinnerCache winnerCache;

        public RpsGame(
            RpsUserSelectionCache selectionCache,
            RpsWinnerCache winnerCache
        ) {
            this.selectionCache = selectionCache;
            this.winnerCache = winnerCache;
        }

        public String play(String chatId) {
            List<RpsUserSelection> selections = selectionCache.get(chatId);
            Optional<RpsUserSelection> userSelectionOptional = vs(selections);
            selectionCache.remove(chatId);

            String gameText = "";
            if (userSelectionOptional.isEmpty()) {
                gameText = getDrawText(selections);
            } else {
                RpsUserSelection winner = userSelectionOptional.get();
                gameText = getWinnerText(winner);
                winnerCache.add(chatId, winner.user().getName());
            }

            Map<String, Integer> winners = winnerCache.get(chatId);
            return currentScore(winners, selections, gameText);
        }

        private Optional<RpsUserSelection> vs(List<RpsUserSelection> selections) {
            RpsUserSelection s1 = selections.get(0);
            RpsUserSelection s2 = selections.get(1);
            int result = s1.rpsType().vs(s2.rpsType());
            if (result == 1) {
                return Optional.of(s1);

            } else if (result == -1) {
                return Optional.of(s2);
            }
            return Optional.empty();
        }

        private String getWinnerText(RpsUserSelection winner) {
            return "%s won with %s!".formatted(winner.user().getName(), winner.rpsType().name());
        }


        private String currentScore(Map<String, Integer> winners, List<RpsUserSelection> selections, String text) {
            RpsUserSelection s1 = selections.get(0);
            RpsUserSelection s2 = selections.get(1);
            String userName1 = s1.user().getName();
            String userName2 = s2.user().getName();

            int user1WinCount = winners.get(userName1);
            int user2WinCount = winners.get(userName2);
            return """
                    %s
                    
                    Current score:
                    %s : %d
                    %s : %d
                    """
                    .formatted(
                        text,
                        userName1,
                        user1WinCount,
                        userName2,
                        user2WinCount
                    );
        }

        private String getDrawText(List<RpsUserSelection> selections) {
            String weapon = getWeapon(selections);
            return "It is draw. You both selected %s.".formatted(weapon);
        }

        private String getWeapon(List<RpsUserSelection> selections) {
            return selections.stream().findFirst()
                .map(RpsUserSelection::rpsType)
                .map(RpsType::name)
                .orElseThrow();
        }
    }
}
