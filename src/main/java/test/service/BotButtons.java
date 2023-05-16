package test.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import test.model.Question;

public interface BotButtons {
    SendMessage addButtonWhatElse(long chatId);

    SendMessage addButtonAndSendMessage(Question question, long chatId);
}
