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

import javax.persistence.EntityNotFoundException;
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
//        result.setId(12);
        result.setUserId(user.getId());
        result.setCountQuestions(0);
        result.setCountRightAnswers(0);
        return resultRepository.save(result);
    }

    @Override
    public String getTop() {
        List<Result> results = resultRepository.findAll();

        StringBuilder top = new StringBuilder("Таблица победителей:\n");

        var sortedResult = results.stream()
                .filter(s -> s.getCountQuestions() != 0)
                .sorted(Comparator.comparing((Result result) -> {
                    return result.getCountRightAnswers() * 100 / result.getCountQuestions();
                }).reversed())
                .limit(10).toList();
        String sendText = "";

        for (int i = 0; i < sortedResult.size(); i++) {
            String name = userComponent.findById(sortedResult.get(i).getUserId()).getFullName();
            Integer countQuestions = sortedResult.get(i).getCountQuestions();

            Integer rightAnswers = sortedResult.get(i).getCountRightAnswers();
            var score = rightAnswers * 100 / countQuestions;
            String defaultText = (i + 1) + ". " + name + " - " + score + " баллов";
            switch (i) {
                case (0) -> sendText = defaultText + " " + EmojiParser.parseToUnicode(":first_place_medal:");
                case (1) -> sendText = defaultText + " " + EmojiParser.parseToUnicode(":second_place_medal:");
                case (2) -> sendText = defaultText + " " + EmojiParser.parseToUnicode(":third_place_medal:");
                default -> sendText = defaultText;
            }

            top.append("\n").append(sendText);
        }

        return top.toString();
    }

    @Override
    public String getMyScore(long chatId) {
        var results = userComponent.findByChatId(chatId);
        var userId = results.getId();
        var countQuestions = getByUserId(userId).getCountQuestions();
        var rightAnswers = getByUserId(userId).getCountRightAnswers();

        return "Мои результаты:\n\n" +
                "всего ответов: " + countQuestions + "\n" +
                "из них правильных: " + rightAnswers;
    }

    @Override
    public Optional<Result> findById(UUID id) {
        return resultRepository.findById(id);
    }

    @Override
    public Result update(Result result) {
        resultRepository.save(result);
        return result;
    }

//    @Override
//    public Result getById(Integer id) {
//        var result = resultRepository.findById(id);
//        return result
//                .orElseThrow(() -> new EntityNotFoundException("Результат с id" + id + " не найден"));
//    }

    @Override
    public Result getByUserId(UUID id){
        var result = resultRepository.findByUserId(id);
        return result.orElseThrow(() -> new EntityNotFoundException("Результат с userId " + id + " не найден"));
    }
}
