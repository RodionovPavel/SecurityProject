package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import test.dto.QuestionRequest;
import test.mapper.QuestionMapper4;
import test.model.Question;
import test.model.Result;
import test.repository.QuestionRepository;
import test.service.QuestionComponent;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionComponentImpl implements QuestionComponent {
    private final QuestionMapper4 questionMapper;

//    private final QuestionComponent questionComponent;

    private final QuestionRepository questionRepository;

    @Override
    public Question add(QuestionRequest requestDto) {
        Question question = questionMapper.fromAddDto2(requestDto);
        log.info("Question '{}' added", question.getId());
        questionRepository.save(question);
        return question;
    }

    @Override
    public Question update(UUID id, QuestionRequest requestDto) {
        log.info("Update user id '{}', userName '{}'", id, requestDto.getTitleQuestion());
        Optional<Question> findQuestion = questionRepository.findById(id);

        if (findQuestion.isPresent()) {
            Question question = questionMapper.fromAddDto2(requestDto);
            questionRepository.save(question);
            return question;
        }
        return null;
    }

    @Override
    public Question getQuestionById(UUID id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Вопрос с id" + id + " не найден"));
    }

    @Override
    public Optional<Question> findByTitleQuestion(String titleQuestion) {
        return questionRepository.findByTitleQuestion(titleQuestion);
    }

    @Override
    public void deleteById(UUID id) {
        questionRepository.deleteById(id);
    }

    public Optional<Question> findById(UUID id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> readAll() {
        return questionRepository.findAll();
    }

    @Override
    public long size() {
        return questionRepository.count();
    }
}
