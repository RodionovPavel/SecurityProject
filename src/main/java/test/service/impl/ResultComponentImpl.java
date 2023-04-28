package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import test.model.Result;
import test.model.User;
import test.repository.ResultRepository;
import test.service.ResultComponent;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResultComponentImpl implements ResultComponent {

    private final ResultRepository resultRepository;

    public Result createResult(User user) {
        Result result = new Result();
        result.setUserId(user.getId());
        result.setCountQuestions(0);
        result.setCountRightAnswers(0);
        resultRepository.save(result);
        return result;
    }
}
