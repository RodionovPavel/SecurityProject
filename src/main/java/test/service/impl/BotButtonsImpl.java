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

    private static final String SEPARATOR = "-----------------------";

    @Override
    public SendMessage addButtonWhatElse(long chatId) {
        var sendMessage = createBaseSendMessage(chatId);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = createRowsInLine(markupInline);
        addButtonsToRows(rowsInLine);
        sendMessage.setReplyMarkup(markupInline);
        return sendMessage;
    }

    private SendMessage createBaseSendMessage(long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setText(SEPARATOR);
        sendMessage.setChatId(chatId);
        return sendMessage;
    }

    private List<List<InlineKeyboardButton>> createRowsInLine(InlineKeyboardMarkup markupInline) {
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        markupInline.setKeyboard(rowsInLine);
        return rowsInLine;
    }

    private void addButtonsToRows(List<List<InlineKeyboardButton>> rowsInLine) {
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();
        var aButton = createInlineKeyboardButton("Следующий вопрос", "NEXT_QUESTION");
        rowInLine1.add(aButton);
        rowsInLine.add(rowInLine1);

        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        var bButton = createInlineKeyboardButton("В меню", "MENU");
        rowInLine2.add(bButton);
        rowsInLine.add(rowInLine2);
    }

    private InlineKeyboardButton createInlineKeyboardButton(String text, String callbackData) {
        var button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }

    @Override
    public SendMessage addButtonAndSendMessage(Question question, long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setText(question.getQuestion());
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(createKeyboardMarkup());
        return sendMessage;
    }

    private InlineKeyboardMarkup createKeyboardMarkup() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        rowInLine.add(addButton("A"));
        rowInLine.add(addButton("B"));
        rowInLine.add(addButton("C"));
        rowInLine.add(addButton("D"));

        rowsInLine.add(rowInLine);
        markupInline.setKeyboard(rowsInLine);
        return markupInline;
    }

    private InlineKeyboardButton addButton(String buttonLabel) {
        var button = new InlineKeyboardButton();
        button.setText(buttonLabel);
        button.setCallbackData(buttonLabel);
        return button;
    }
}
