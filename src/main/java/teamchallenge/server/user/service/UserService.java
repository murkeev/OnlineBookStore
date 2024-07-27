package teamchallenge.server.user.service;

import teamchallenge.server.auth.dto.CreateUserDto;
import teamchallenge.server.user.dto.ResponseUserDto;
import teamchallenge.server.user.entity.User;

public interface UserService {

    void createUser(CreateUserDto createUserDto);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    void save(User user);

    ResponseUserDto getUserById(Long id);
}
