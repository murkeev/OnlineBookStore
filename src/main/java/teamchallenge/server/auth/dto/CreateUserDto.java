package teamchallenge.server.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import teamchallenge.server.auth.annotation.UniqueEmail;

@Data
public class CreateUserDto {
    @NotBlank
    @Email
    @UniqueEmail(message = "Email already exists")
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Size(min = 8)
    private String password;

    private Long cartHeaderId;
}
