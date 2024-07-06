package teamchallenge.server.user;

import teamchallenge.server.auth.CreateUserDto;

public interface UserService {

    void createUser(CreateUserDto createUserDto);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    void save(User user);

    ResponseUserDto getUserById(Long id);
}
