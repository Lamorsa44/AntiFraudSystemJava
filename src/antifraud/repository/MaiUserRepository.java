package antifraud.repository;

import antifraud.entity.user.MaiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface MaiUserRepository extends JpaRepository<MaiUser, Long> {
    boolean existsMaiUserByUsernameIgnoreCase(String username);
    Optional<MaiUser> findByUsernameIgnoreCase(String username);
}
