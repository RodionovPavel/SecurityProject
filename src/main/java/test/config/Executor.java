package test.config;

import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import test.model.enums.BotCommands;
import test.model.enums.Role;
import test.model.User;
import test.service.BotButtons;
import test.service.QuestionComponent;
import test.service.ResultComponent;
import test.service.UserComponent;

import java.time.LocalDateTime;


@Slf4j
@Component
@RequiredArgsConstructor
public class Executor {

    static final String START_TEXT = "Привет! Меня зовут Сеня, и я самый экспертный java-бот в мире." + ":blush:" + "\n\nВ меня загрузили:\n" +
            "· более 1000 реальных задач\n" +
            "· сотню вопросов с реальных собеседований\n" +
            "Знаю и умею я много. Постарайся ежедневно отвечать на вопросы, читай рекомендации к ответам и ты отметишь свой быстрый профессиональный рост уже через пару недель!\n" +
            "\n Для начала зарегистрируйся, чтобы мы познакомились и я добавил тебя в рейтинг /register";

    public static final String HELP_TEXT = "Этот бот поможет тебе поддерживать актуальные знания в Java.\n\n" +
            "У тебя все обязательно получится! " + EmojiParser.parseToUnicode(":muscle:") + "\n\n" +
            BotCommands.REGISTER.getCommand() + " - " + BotCommands.REGISTER.getDescription() + "\n" +
            BotCommands.RANDOM.getCommand() + " - " + BotCommands.RANDOM.getDescription() + "\n" +
            BotCommands.TOP.getCommand() + " - " + BotCommands.TOP.getDescription() + "\n" +
            BotCommands.SCORE.getCommand() + " - " + BotCommands.SCORE.getDescription();

    private final UserComponent userComponent;

    private final ResultComponent resultComponent;

    private final QuestionComponent questionComponent;

    private final BotButtons botButtons;


    @Bean
    public MessageProcessor start() {
        return new MessageProcessor() {
            @Override
            public SendMessage execute(Long chatId, Message msg) {
                String answer = EmojiParser.parseToUnicode(START_TEXT);
                log.info("Новый пользователь " + msg.getChat().getFirstName());
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText(answer);
                return message;
            }

            @Override
            public boolean isSupported(String commands) {
                return BotCommands.START.getCommand().equals(commands);
            }
        };
    }

    @Bean
    public MessageProcessor random() {
        return new MessageProcessor() {

            @Override
            public SendMessage execute(Long chatId, Message msg) {
                var randomQuestion = questionComponent.getRandomQuestion();
                return botButtons.addButtonAndSendMessage(randomQuestion, chatId);
            }

            @Override
            public boolean isSupported(String commands) {
                return BotCommands.RANDOM.getCommand().equals(commands);
            }
        };
    }

    @Bean
    public MessageProcessor register() {
        return new MessageProcessor() {
            @Override
            public SendMessage execute(Long chatId, Message msg) {
                SendMessage message = new SendMessage();
                message.setChatId(String.valueOf(chatId));

                if (userComponent.findByChatId(chatId).isEmpty()) {
                    var chat = msg.getChat();
                    var newUser = createDefaultUser(chatId, chat.getUserName());

                    resultComponent.create(newUser);
                    log.info("Добавлено в результат");

                    log.info("Пользователь зарегистрирован: " + chat.getUserName());
                    message.setText("Отлично! " + chat.getUserName() + ", Вы зарегистрированы в нашем рейтинге и можете отслеживать результаты по команде /score\n" +
                            "\n Чтобы начать отвечать на вопросы - нажмите /random");
                } else {
                    message.setText("Вы уже зарегистрированы в нашем рейтинге\n\n" +
                            "Смело переходи к вопросам /random");
                }

                return message;
            }

            @Override
            public boolean isSupported(String commands) {
                return BotCommands.REGISTER.getCommand().equals(commands);
            }
        };
    }

    @Bean
    public MessageProcessor help() {
        return new MessageProcessor() {
            @Override
            public SendMessage execute(Long chatId, Message msg) {
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText(HELP_TEXT);
                return message;
            }

            @Override
            public boolean isSupported(String commands) {
                return BotCommands.HELP.getCommand().equals(commands);
            }
        };
    }

    @Bean
    public MessageProcessor top() {
        return new MessageProcessor() {
            @Override
            public SendMessage execute(Long chatId, Message msg) {
                String top = resultComponent.getTop();
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText(top);
                return message;
            }

            @Override
            public boolean isSupported(String commands) {
                return BotCommands.TOP.getCommand().equals(commands);
            }
        };
    }

    @Bean
    public MessageProcessor score() {
        return new MessageProcessor() {
            @Override
            public SendMessage execute(Long chatId, Message msg) {
                String sendText = resultComponent.getMyScore(chatId);
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText(sendText);
                return message;
            }

            @Override
            public boolean isSupported(String commands) {
                return BotCommands.SCORE.getCommand().equals(commands);
            }
        };
    }

    private User createDefaultUser(long chatId, String name) {
        var newUser = new User();

        newUser.setChatId(chatId);
        newUser.setLogin(name);
        newUser.setFullName(name);
        newUser.setRole(Role.USER);
        newUser.setCreateDate(LocalDateTime.now());

        userComponent.create(newUser);
        return newUser;
    }
}
