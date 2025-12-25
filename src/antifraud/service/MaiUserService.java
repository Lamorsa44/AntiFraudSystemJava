package antifraud.service;

import antifraud.entity.user.*;
import antifraud.exception.ConflictException;
import antifraud.exception.NotFoundException;
import antifraud.repository.MaiUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class MaiUserService implements UserDetailsService {

    private final MaiUserRepository maiUserRepository;
    private final PasswordEncoder passwordEncoder;

    public MaiUserService(MaiUserRepository maiUserRepository, PasswordEncoder passwordEncoder) {
        this.maiUserRepository = maiUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public MaiUser save(MaiUserRequest request) {
        if (maiUserRepository.existsMaiUserByUsernameIgnoreCase(request.username())) {
            throw new ConflictException("Username already exists");
        }

        if (maiUserRepository.count() == 0) {
            MaiUser user = request.encode(passwordEncoder, Role.ROLE_ADMINISTRATOR);
            user.setLock(Lock.UNLOCK);
            return maiUserRepository.save(user);
        } else {
            return maiUserRepository.save(request.encode(passwordEncoder, Role.ROLE_MERCHANT));
        }
    }

    private MaiUser getUserByUsername(String username) {
        return maiUserRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new NotFoundException(username));
    }

    public UserDeleteResponse delete(String username) {
        MaiUser user = getUserByUsername(username);
        maiUserRepository.delete(user);
        return new UserDeleteResponse(user);
    }

    public List<MaiUserResponse> listUsers() {
        return maiUserRepository.findAll().stream()
                .map(MaiUserResponse::new).toList();
    }

    public MaiUserResponse updateRole(RoleRequest request) {
        MaiUser user = getUserByUsername(request.username());

        if (user.getRole().equals(request.getRole())) {
            throw new ConflictException("Role already given");
        }

        user.setRole(request.getRole());
        maiUserRepository.save(user);
        return new MaiUserResponse(user);
    }

    public LockingResponse lockUser(LockingRequest request) {
        MaiUser user = getUserByUsername(request.username());
        if (user.getRole().equals(Role.ROLE_ADMINISTRATOR)) {
            throw new IllegalArgumentException("Cannot lock admin user");
        }
        user.setLock(request.getOperation());
        maiUserRepository.save(user);
        return new LockingResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MaiUserAdapter(getUserByUsername(username));
    }
}
