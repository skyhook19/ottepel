package net.service.converters;

import net.domain.User;
import net.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConverterUsers {
    public List<UserDto> convertToUserDto(List<User> users) {
        return users.stream().map(this::userToUserDto).collect(Collectors.toList());
    }

    private UserDto userToUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .login(user.getLogin())
                .name(user.getName())
                .roles(user.getRoles()).build();
    }
}
