package kz.kaisar.springcrudapp02.controller;

import kz.kaisar.springcrudapp02.dto.UserDto;
import kz.kaisar.springcrudapp02.exception.ValidationException;
import kz.kaisar.springcrudapp02.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Log
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
//            log.info("Handling save users: " + userDto); должен быть в сервис
        return userService.saveUser(userDto);
    }

    @GetMapping("/findAll")
    public List<UserDto> findAllUsers() {
//        log.info("Handling find all users request");
        return userService.findAll();
    }

    @GetMapping("/findByLogin")
    public UserDto findByLogin(@RequestParam String login) {
//        log.info("Handling find by login request" + login);
        return userService.findByLogin(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Integer id) { // Должен быть, long
//        log.info("Handling delete user request" + id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
