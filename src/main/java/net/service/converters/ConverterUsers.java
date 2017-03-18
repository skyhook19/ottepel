package net.service.converters;

import net.domain.users.User;
import net.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConverterUsers {
    public List<UserDto> convertToUserDto(List<User> users) {
        return users.stream().map(this::userToCollaboratorDto).collect(Collectors.toList());
    }

    public UserDto userToCollaboratorDto(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .roles(user.getRoles())
                .email(user.getEmail())
                .build();
    }
}
