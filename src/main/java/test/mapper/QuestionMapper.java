package test.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import test.dto.QuestionRequest;
import test.model.Question;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface QuestionMapper { //todo один мапер
    Question fromAddDto2(QuestionRequest dto);
}