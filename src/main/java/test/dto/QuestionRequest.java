package test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Сущность вопроса")
public class QuestionRequest {


    @Schema(description = "Номер вопроса в очереди", example = "3")
    private Integer weight;

    @NotBlank
    @Size(min = 7, max = 4000)
    @Schema(description = "Краткое название вопроса", example = "Название языка")
    private String titleQuestion;

    @NotBlank
    @Size(min = 7, max = 4000)
    @Schema(description = "Вопрос", example = "Кто придумал название языка Java?")
    private String question;

    @NotBlank
    @Size(min = 7, max = 4000)
    @Schema(description = "Ответ", example = "int - примитив")
    private String answer;

    @NotBlank
    @Size(min = 7, max = 4000)
    @Schema(description = "Ответ, если все верно", example = "Отлично! Так держать)")
    private String ifRightAnswer;

    @NotBlank
    @Size(min = 7, max = 4000)
    @Schema(description = "Ответ, если неверно", example = "Почти! На экран будет выведен 0.")
    private String ifWrongAnswer;
}
