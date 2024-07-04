package teamchallenge.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import teamchallenge.server.annotation.UniqueEmail;


@Data
public class CreateUserDto {

    @NotBlank
    @Email
    @UniqueEmail(message = "Email already exists")
    public String email;
    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;

    @NotBlank
    @Size(min = 8)
    public String password;

    public Long cartHeaderId;
}
