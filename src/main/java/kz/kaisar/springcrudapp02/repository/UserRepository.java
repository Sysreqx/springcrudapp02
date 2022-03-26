package kz.kaisar.springcrudapp02.repository;

import kz.kaisar.springcrudapp02.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { // long вместо ште


    User findByLogin(String login);
}
