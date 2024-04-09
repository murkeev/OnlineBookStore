package teamchallenge.server.services;

import teamchallenge.server.dto.CreateUserDto;
import teamchallenge.server.entities.User;

import java.util.Optional;

public interface UserService {

    void createUser(CreateUserDto createUserDto);
    boolean existsByEmail(String email);

    User findByEmail(String email);

    void save(User user);

}
