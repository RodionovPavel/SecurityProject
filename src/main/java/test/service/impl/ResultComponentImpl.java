package test.service.impl;

import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import test.model.Result;
import test.model.User;
import test.repository.ResultRepository;
import test.service.ResultComponent;
import test.service.UserComponent;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResultComponentImpl implements ResultComponent {

    private final ResultRepository resultRepository;
    private final UserComponent userComponent;

    @Override
    public Result create(User user) {
        Result result = new Result();
        result.setUserId(user.getId());
        result.setCountQuestions(0);
        result.setCountRightAnswers(0);
        resultRepository.save(result);
        return result;
    }

    @Override
    public String getTop() {
        List<Result> results = resultRepository.findAll();

        String top = "Таблица победителей:\n";

        var sortedResult = results.stream()
                .filter(s -> s.getCountQuestions() != 0)
                .sorted(Comparator.comparing((Result result) -> {
                    return result.getCountRightAnswers() * 100 / result.getCountQuestions();
                }).reversed())
                .limit(10).toList();
        String sendText = "";

        for (int i = 0; i < sortedResult.size(); i++) {
            String name = userComponent.findById(sortedResult.get(i).getUserId()).get().getFullName();
            Integer countQuestions = sortedResult.get(i).getCountQuestions();
            log.info(String.valueOf(countQuestions));

            Integer rightAnswers  = sortedResult.get(i).getCountRightAnswers();
            log.info(String.valueOf(rightAnswers));
            Integer score = rightAnswers * 100 / countQuestions;
            String defaultText = (i + 1) + ". " + name + " - " + score + " баллов";
            switch (i) {
                case (0) -> sendText = defaultText + " " + EmojiParser.parseToUnicode(":first_place_medal:");
                case (1) -> sendText = defaultText + " " + EmojiParser.parseToUnicode(":second_place_medal:");
                case (2) -> sendText = defaultText + " " + EmojiParser.parseToUnicode(":third_place_medal:");
                default -> sendText = defaultText;
            }
            top += "\n" + sendText;
            log.info(top);
        }
        return top;
    }

    @Override
    public String getMyScore(long chatId) {
        var results = userComponent.findByChatId(chatId);
        var userId = results.get().getId();
        var countQuestions = resultRepository.findById(userId).get().getCountQuestions();
        var rightAnswers = resultRepository.findById(userId).get().getCountRightAnswers();

        return "Мои результаты:\n\n" +
                "всего ответов: " + countQuestions + "\n" +
                "из них правильных: " + rightAnswers;
    }

    @Override
    public Optional<Result> findById(UUID id) {
        var result = resultRepository.findById(id);
        return result;
    }

    @Override
    public void update(Result result) {
        resultRepository.save(result);
    }

}
