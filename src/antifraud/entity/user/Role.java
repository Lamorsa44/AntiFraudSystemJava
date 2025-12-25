package antifraud.entity.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public enum Role {
    ROLE_ANONYMOUS,
    ROLE_MERCHANT,
    ROLE_SUPPORT,
    ROLE_ADMINISTRATOR;

    public Collection<GrantedAuthority> getAuthority() {
        return Collections.singleton(this::name);
    }

    public static Role parse(String role) {
        return Role.valueOf("ROLE_" + role);
    }

    @Override
    public String toString() {
        return name().substring(5);
    }
}
