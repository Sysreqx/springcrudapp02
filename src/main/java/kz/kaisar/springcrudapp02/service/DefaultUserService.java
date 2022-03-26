package kz.kaisar.springcrudapp02.service;

import kz.kaisar.springcrudapp02.dto.UserDto;
import kz.kaisar.springcrudapp02.entity.User;
import kz.kaisar.springcrudapp02.exception.ValidationException;
import kz.kaisar.springcrudapp02.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class DefaultUserService implements UserService{

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    private void validateUserDto(UserDto userDto) throws ValidationException {
        if (isNull(userDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(userDto.getLogin()) || userDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }

    @Override
    public UserDto saveUser(UserDto userDto) throws ValidationException {
        validateUserDto(userDto);
        User savedUser = (User) userRepository.save(userConverter.fromUserDtoToUser(userDto));
        return userConverter.fromUserToUserDto(savedUser);
    }


    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return userConverter.fromUserToUserDto(user);
        }
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();

        if (users != null) {
            for (User u: users) {
                usersDto.add(userConverter.fromUserToUserDto(u));
            }
        }
        return userRepository.findAll();
    }
}
