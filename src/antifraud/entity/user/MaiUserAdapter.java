package antifraud.entity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MaiUserAdapter implements UserDetails {
    private final MaiUser maiUser;

    public MaiUserAdapter(MaiUser maiUser) {
        this.maiUser = maiUser;
    }

    public Long getId() {
        return maiUser.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return maiUser.getRole().getAuthority();
    }

    @Override
    public String getPassword() {
        return maiUser.getPassword();
    }

    @Override
    public String getUsername() {
        return maiUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !maiUser.getLock().isLock();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
