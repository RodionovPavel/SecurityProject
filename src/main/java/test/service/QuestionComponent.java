package test.service;

import test.dto.QuestionRequest;
import test.model.Question;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionComponent {
    Question add(QuestionRequest request);

    Question update(UUID id, QuestionRequest request);

    Question getQuestionById(UUID id);

    Optional<Question> findByTitleQuestion(String titleQuestion);

    Optional<Question> findById(UUID id);

    void deleteById(UUID id);

    List<Question> readAll();

    long size();

    Question getRandomQuestion();
}
