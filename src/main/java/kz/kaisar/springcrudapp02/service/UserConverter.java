package kz.kaisar.springcrudapp02.service;

import kz.kaisar.springcrudapp02.dto.UserDto;
import kz.kaisar.springcrudapp02.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

public class UserConverter {

    public User fromUserDtoToUser(UserDto userDto) {

        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .login(userDto.getLogin())
                .name(userDto.getName())
                .build();
    }

    public UserDto fromUserToUserDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .login(user.getLogin())
                .name(user.getName())
                .build();
    }
}
