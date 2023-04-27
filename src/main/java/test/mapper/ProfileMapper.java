package test.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import test.dto.ClientRegisterRequest;
import test.model.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ProfileMapper {
    User fromRegisterDto(ClientRegisterRequest dto);
}
