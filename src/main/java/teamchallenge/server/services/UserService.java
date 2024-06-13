package teamchallenge.server.services;

import teamchallenge.server.dto.CreateUserDto;
import teamchallenge.server.dto.ResponseUserDto;
import teamchallenge.server.entities.User;

public interface UserService {

    void createUser(CreateUserDto createUserDto);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    void save(User user);

    ResponseUserDto getUserById(Long id);
}
