package kz.kaisar.springcrudapp02.service;

import kz.kaisar.springcrudapp02.dto.UserDto;
import kz.kaisar.springcrudapp02.entity.User;
import kz.kaisar.springcrudapp02.exception.ValidationException;
import kz.kaisar.springcrudapp02.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Service
public class DefaultUserService implements UserService{

    @Autowired
    private final UserRepository userRepository;
    @Autowired
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
    public ResponseEntity<?> saveUser(UserDto userDto){
        User savedUser = User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .login(userDto.getLogin())
                .name(userDto.getName())
                .build();

                userRepository.save(savedUser);

        return ResponseEntity.ok(userConverter.fromUserToUserDto(savedUser));

    }

    @Transactional
    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
    // нЕ СРАботает удаление должен быть transactional


    public void deleteUser(Long userId) {
        userRepository.findById(userId.intValue()).ifPresent(userRepository::delete);
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

        if (!users.isEmpty()) {
            for (User user: users) {
                usersDto.add(userConverter.fromUserToUserDto(user));
            }
        }
        return usersDto;
    }

    // Получить всех user у которых в почтовом адресе. "передаваемый параметр *@gmail. и т.д.*, отсортиванный по id"

    // EditUser
    // EditUser
    // AddUser
    // DeleteUsers


}
