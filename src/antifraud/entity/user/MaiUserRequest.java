package antifraud.entity.user;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.crypto.password.PasswordEncoder;

public record MaiUserRequest(@NotEmpty String name,
                             @NotEmpty String username,
                             @NotEmpty String password) {
    public MaiUser encode(PasswordEncoder encoder, Role role) {
        return new MaiUser(name, username, encoder.encode(password), role);
    }
}
