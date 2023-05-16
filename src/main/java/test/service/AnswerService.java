package test.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import test.model.Question;

public interface AnswerService {
    SendMessage checkAnswer(Long chatId, Question question, String callbackData);
}
