package test.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import test.dto.RegisterDto;
import test.model.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProfileMapper {
    User fromRegisterDto(RegisterDto dto);
}
