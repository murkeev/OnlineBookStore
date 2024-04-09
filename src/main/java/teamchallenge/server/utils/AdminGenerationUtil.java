package teamchallenge.server.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import teamchallenge.server.entities.User;
import teamchallenge.server.services.RoleService;
import teamchallenge.server.services.UserService;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AdminGenerationUtil {

    private final UserService userService;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

//    @Scheduled(fixedRate = 10000)
    public void generateAdmin() {
        User admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setRoles(List.of(roleService.findByName("ROLE_ADMIN")));
        admin.setPassword(passwordEncoder.encode("Secret"));
        userService.save(admin);
        System.out.println("Admin generated");
    }

}
