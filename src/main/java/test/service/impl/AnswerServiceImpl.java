package test.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import test.model.Question;
import test.service.AnswerService;
import test.service.BotButtons;
import test.service.ResultComponent;
import test.service.UserComponent;

import static test.config.Executor.HELP_TEXT;

@Slf4j
@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final UserComponent userComponent;

    private final ResultComponent resultComponent;

    private final BotButtons botButtons;

    @Override
    public SendMessage checkAnswer(Long chatId, Question question, String callbackData) {
        String rightAnswer = question.getRightAnswer();

        switch (callbackData) {
            case "A", "B", "C", "D" -> {
                var user = userComponent.getByChatId(chatId);
                var myResult = resultComponent.getByUserId(user.getId());
                var currentCountQuestions = myResult.getCountQuestions();
                myResult.setCountQuestions(currentCountQuestions + 1);
                resultComponent.update(myResult);
                var answer = question.getIfWrongAnswer();

                if (callbackData.equals(rightAnswer)) {
                    var currentRightAnswer = myResult.getCountRightAnswers();
                    myResult.setCountRightAnswers(currentRightAnswer + 1);
                }

                resultComponent.update(myResult);
                return sendMessage(chatId, answer);
            }

            case "NEXT_QUESTION" -> {
                return botButtons.addButtonAndSendMessage(question, chatId);
            }
            case "MENU" -> {
                return sendMessage(chatId, HELP_TEXT);
            }
            default -> {
                return sendMessage(chatId, "Моя твоя не понимать");
            }
        }
    }

    private SendMessage sendMessage(long chatId, String textToSend) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        return message;

    }
}
