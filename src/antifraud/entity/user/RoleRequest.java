package antifraud.entity.user;

import jakarta.validation.constraints.NotEmpty;

public record RoleRequest(@NotEmpty String username, @NotEmpty String role) {
    public RoleRequest {
        if (!role.equals("SUPPORT") && !role.equals("ADMIN"))
            throw new IllegalArgumentException();
    }

    public Role getRole() {
        return Role.parse(role);
    }
}
