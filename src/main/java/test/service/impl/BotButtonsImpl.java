package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import test.model.Question;
import test.service.BotButtons;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BotButtonsImpl implements BotButtons {

    @Override
    public SendMessage addButtonWhatElse(long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setText("-----------------------");
        sendMessage.setChatId(chatId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var aButton = new InlineKeyboardButton();
        aButton.setText("Следующий вопрос");
        aButton.setCallbackData("NEXT_QUESTION");

        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        var bButton = new InlineKeyboardButton();
        bButton.setText("В меню");
        bButton.setCallbackData("MENU");

        rowInLine.add(aButton);
        rowInLine2.add(bButton);

        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);

        markupInline.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(markupInline);

        return sendMessage;
    }

    @Override
    public SendMessage addButtonAndSendMessage(Question question, long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setText(question.getQuestion());
        sendMessage.setChatId(chatId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var aButton = new InlineKeyboardButton();
        aButton.setText("A");
        aButton.setCallbackData("A");

        var bButton = new InlineKeyboardButton();
        bButton.setText("B");
        bButton.setCallbackData("B");

        var cButton = new InlineKeyboardButton();
        cButton.setText("C");
        cButton.setCallbackData("C");

        var dButton = new InlineKeyboardButton();
        dButton.setText("D");
        dButton.setCallbackData("D");

        rowInLine.add(aButton);
        rowInLine.add(bButton);
        rowInLine.add(cButton);
        rowInLine.add(dButton);

        rowsInLine.add(rowInLine);

        markupInline.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(markupInline);

        return sendMessage;
    }
}
