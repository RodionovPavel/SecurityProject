package test.service.impl;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import test.config.BotConfig;
import test.model.Role;
import test.model.User;
import test.repository.QuestionRepository;
import test.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    private final UserRepository userRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    static final String HELP_TEXT = "Этот бот поможет тебе поддерживать актуальные знания в Java.\n\n" +
            "У тебя все обязательно получится!\n\n" +
            "Выбери /start для начала вопросов";

    public TelegramBot(BotConfig config, UserRepository userRepository, QuestionRepository questionRepository) throws IOException {
        this.config = config;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        List<BotCommand> listOfCommands = new ArrayList();
        listOfCommands.add(new BotCommand("/start", "Начать отвечать на вопросы без регистрации"));
        listOfCommands.add(new BotCommand("/question", "Получить вопрос"));
        listOfCommands.add(new BotCommand("/register", "Зарегистрироваться"));
        listOfCommands.add(new BotCommand("/top", "Показать общий рейтинг"));
        listOfCommands.add(new BotCommand("/mytop", "Показать мой личный рейтинг"));
        listOfCommands.add(new BotCommand("/help", "Помощь"));

        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }

//        ObjectMapper objectMapper = new ObjectMapper();
//        TypeFactory typeFactory = objectMapper.getTypeFactory();
//        List<Question> questionList = objectMapper.readValue(new File("test/data/question.json"), typeFactory.constructCollectionType(List.class, Question.class));
//        this.questionRepository.saveAll(questionList);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.contains("/send") && chatId == 262737006) {
                var textToSend = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
                var users = userRepository.findAll();
                for (User user : users) {
                    sendMessage(user.getChatId(), textToSend);
                }
            }
                switch (messageText) {
                    case "/start" -> startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    case "/random" -> {

//                        var random = new Random();
//                        var randomId = random.nextLong(userRepository.count() + 1);

//                        var question = questionRepository.findByTitleQuestion("Первый вопрос");
//                        var question = questionRepository.findById("45760888-6d39-4116-8b31-f475c3c0e507");
//                        log.info(question.get().getQuestion());
//                        sendMessage(chatId, question.get().getQuestion());
                        var randomQuestion = getRandomQuestion();
                        addButtonAndSendMessage(randomQuestion, chatId);

                    }
                    case "/help" -> sendMessage(chatId, HELP_TEXT);
                    case "/register" -> register(chatId);
                    case "/top" -> getTop(chatId);
                    case "/registerUser" -> registerUser(update.getMessage());
                    default -> sendMessage(chatId, "Sorry");
                }
            }

            else if (update.hasCallbackQuery()) {
                String callbackData = update.getCallbackQuery().getData();
                long chatId = update.getCallbackQuery().getMessage().getChatId();

                if (callbackData.equals("NEXT_QUESTION")) {
                    var randomQuestion = getRandomQuestion();
                    addButtonAndSendMessage(randomQuestion, chatId);
                }
            }

    }

    private void addButtonAndSendMessage(String question, long chatId) {
        var sendMessage = new SendMessage();
        sendMessage.setText(question);
        sendMessage.setChatId(chatId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowLine = new ArrayList<>();
        var firstButton = new InlineKeyboardButton();
        firstButton.setCallbackData("FIRST_ANSWER");
        firstButton.setText(EmojiParser.parseToUnicode("Да " + ":thumbsup:"));
        rowLine.add(firstButton);

        var secondButton = new InlineKeyboardButton();
        secondButton.setCallbackData("FIRST_ANSWER");
        secondButton.setText(EmojiParser.parseToUnicode("Нет " + ":thumbsdown:"));
        rowLine.add(secondButton);

        rowsInLine.add(rowLine);
        markupInline.setKeyboard(rowsInLine);
        sendMessage.setReplyMarkup(markupInline);
        executeMessage(sendMessage);
    }


    private String getRandomQuestion() {
        var question = questionRepository.findAll();
        var random = new Random();
        var randomId = random.nextLong(questionRepository.count());

        return question.get((int) randomId).getQuestion();

    }

    private void startCommandReceived(long chatId, String name) {

        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you!" + ":blush:");
        log.info("Новый пользователь " + name);
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        executeMessage(message);
    }

    private void register(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Вы хотите зарегистрироваться?");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var yesButton = new InlineKeyboardButton();
        yesButton.setText("Да");
        yesButton.setCallbackData("YES_BUTTON");

        var noButton = new InlineKeyboardButton();
        noButton.setText("Нет");
        noButton.setCallbackData("NO_BUTTON");

        rowInLine.add(yesButton);
        rowInLine.add(noButton);

        rowsInLine.add(rowInLine);

        markupInline.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInline);


    }

    private void getTop(Long chatId) {
        var users = userRepository.findAll();
        for (User user : users) {
            sendMessage(chatId, user.getFullName());
        }

    }

    private void registerUser(Message msg) {

        if (userRepository.findByChatId(msg.getChatId()).isEmpty()) {
            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setLogin(chat.getUserName());
            user.setId(UUID.randomUUID());
            user.setFullName("Павел Анатольевич");
            user.setPassword("123");
            user.setRole(Role.USER);
            user.setPhone("79991231233");
            user.setEmail("email@email.ru");
            user.setCreateDate(LocalDateTime.now());

            userRepository.save(user);
            log.info("Пользователь сохранен: " + user);
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
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }
}
