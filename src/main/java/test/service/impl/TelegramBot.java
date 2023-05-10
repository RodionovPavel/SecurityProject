package test.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import test.config.BotConfig;
import test.config.MessageProcessor;
import test.service.*;

import java.util.*;

@Slf4j
@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    private final QuestionComponent questionComponent;

    private final List<MessageProcessor> messageProcessors;

    private final AnswerService answerService;

    private final BotButtons botButtons;

    List<BotCommand> listOfCommands;
    private SendMessage message;


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            messageProcessors.stream().filter(processor -> processor.isSupported(messageText))
                    .forEach(processor -> executeMessage(processor.execute(update.getMessage().getChatId(), update.getMessage())));
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            var randomQuestion = questionComponent.getRandomQuestion();
            executeMessage(answerService.checkAnswer(chatId, randomQuestion, callbackData));
            executeMessage(botButtons.addButtonWhatElse(chatId));
        }

    }


    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    private void executeMessage(SendMessage message) {
        this.message = message;
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }
}
