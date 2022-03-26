package kz.kaisar.springcrudapp02.service;

import kz.kaisar.springcrudapp02.dto.UserDto;
import kz.kaisar.springcrudapp02.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto)  throws ValidationException;

    void deleteUser(int userId);

    UserDto findByLogin(String login);

    List<UserDto> findAll();
}
