package antifraud.repository;

import antifraud.entity.validation.BannedIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannedIpRepository extends JpaRepository<BannedIP, Long> {
    boolean existsByIp(String ip);
    void deleteByIp(String ip);
}
