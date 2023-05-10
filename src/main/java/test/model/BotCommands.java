package test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BotCommands {
    START("/start", "Нажмите, чтобы запустить бота"),
    RANDOM("/random", "Получить вопрос"),
    REGISTER("/register", "Зарегистрироваться, чтобы участвовать в рейтинге"),
    TOP("/top", "Показать общий рейтинг"),
    SCORE("/score", "Показать мой личный результат"),
    HELP("/help", "Помощь");


    private final String command;
    private final String description;


}
