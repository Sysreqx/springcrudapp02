package kz.kaisar.springcrudapp02.dto;

import kz.kaisar.springcrudapp02.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static java.util.Objects.isNull;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private String login;
    private String email;
}
