package test.service;

import test.model.Result;
import test.model.User;

import java.util.Optional;
import java.util.UUID;

public interface ResultComponent {
    Result create(User user);

    String getTop();

    String getMyScore(long chatId);

    Optional<Result> findById(UUID id);

    Result update(Result result);

    Result getByUserId(UUID id);
}
