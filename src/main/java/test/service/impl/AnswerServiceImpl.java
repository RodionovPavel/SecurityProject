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
                var user = userComponent.findByChatId(chatId);
                var myResult = resultComponent.findById(user.get().getId()); //todo обработка .get
                var currentCountQuestions = myResult.get().getCountQuestions();
                myResult.get().setCountQuestions(currentCountQuestions + 1);

                if (callbackData.equals(rightAnswer)) {
                    var currentRightAnswer = myResult.get().getCountRightAnswers();
                    myResult.get().setCountRightAnswers(currentRightAnswer + 1);
                    resultComponent.update(myResult.get());


                    return sendMessage(chatId, question.getIfRightAnswer());
                } else {

                    return sendMessage(chatId, question.getIfWrongAnswer());
                }
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
