package teamchallenge.server.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import teamchallenge.server.auth.dto.CreateUserDto;
import teamchallenge.server.cart.service.CartService;
import teamchallenge.server.role.service.RoleService;
import teamchallenge.server.user.dto.ResponseUserDto;
import teamchallenge.server.user.exception.UserNotFoundException;
import teamchallenge.server.user.entity.User;
import teamchallenge.server.user.entity.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;


    private CartService cartService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void cartService(@Lazy CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void passwordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public ResponseUserDto getUserById(Long id) {
        return userRepository.findById(id).map(this::mapToResponseUserDto).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }


    @Override
    @Transactional
    public void createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user.setRoles(List.of(roleService.findByName("ROLE_PERSONAL")));

        if (createUserDto.getCartHeaderId() != null) {
            user.setCartHeader(cartService.addUserToCart(user, createUserDto.getCartHeaderId()));
        } else {
            user.setCartHeader(cartService.createCart(user));
        }

        userRepository.save(user);

        //findByEmail(user.getEmail()).getId();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private ResponseUserDto mapToResponseUserDto(User user) {
        return ResponseUserDto
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
