package teamchallenge.server.controllers.open;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamchallenge.server.dto.CreateUserDto;
import teamchallenge.server.dto.JwtResponseDto;
import teamchallenge.server.dto.LoginUserDto;
import teamchallenge.server.services.UserService;
import teamchallenge.server.utils.JwtUtils;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth/")
@Validated
public class AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginUserDto loginUserDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword())
        );
        String jwt = jwtUtils.generateToken(userService.findByEmail(loginUserDto.getEmail()));
        return ResponseEntity.ok(new JwtResponseDto(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDto> register(@RequestBody @Valid CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        String jwt = jwtUtils.generateToken(userService.findByEmail(createUserDto.getEmail()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponseDto(jwt));
    }
}
