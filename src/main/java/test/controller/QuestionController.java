package test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import test.dto.QuestionRequest;
import test.model.Question;
import test.service.QuestionComponent;

import javax.validation.Valid;
import java.util.UUID;

@Log4j2
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionComponent questionComponent;

    @Tag(name = "Добавление вопроса", description = "Добавление нового вопроса")
    @Operation(
            summary = "Добавление вопроса",
            description = "Позволяет добавить вопрос"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вопрос добавлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка")})
    @PostMapping("/add")
    public void add(@Valid @RequestBody QuestionRequest request) {
        questionComponent.add(request);
        log.info("Added new question: '{}'", request.getTitleQuestion());
    }

    @GetMapping(value = "/{id}")
    public Question getById(@PathVariable UUID id) {

        Question question = questionComponent.getQuestionById(id);
        log.info("Request question with id: '{}'", id);
        return question;
    }

    @PutMapping(value = "/{id}")
    public void updateQuestion(
            @Parameter(description = "Идентификатор вопроса", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Новые данные вопроса", required = true)
            @RequestBody QuestionRequest request) {
        questionComponent.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable UUID id) {
        log.info("Deleted question with id: '{}'", id);
        questionComponent.deleteById(id);
    }
}
