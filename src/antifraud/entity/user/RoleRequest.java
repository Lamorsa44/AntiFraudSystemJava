package antifraud.entity.user;

import jakarta.validation.constraints.NotEmpty;

import java.util.Arrays;

public record RoleRequest(@NotEmpty String username, @NotEmpty String role) {
    public RoleRequest {
        if (Arrays.stream(Role.values()).filter(r -> !r.equals(Role.ROLE_ADMINISTRATOR))
                .noneMatch(r -> r.toString().equals(role)))
            throw new IllegalArgumentException();
    }

    public Role getRole() {
        return Role.parse(role);
    }
}