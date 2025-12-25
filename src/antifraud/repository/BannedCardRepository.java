package antifraud.repository;

import antifraud.entity.validation.BannedCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannedCardRepository extends JpaRepository<BannedCard, Long> {
    boolean existsByNumber(String number);

    void deleteByNumber(String number);
}
