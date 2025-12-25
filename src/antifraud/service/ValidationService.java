package antifraud.service;

import antifraud.entity.validation.*;
import antifraud.exception.ConflictException;
import antifraud.exception.NotFoundException;
import antifraud.repository.BannedCardRepository;
import antifraud.repository.BannedIpRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static antifraud.entity.validation.Result.OPTION.*;
import static antifraud.service.ValidationService.Reason.*;

@Service
@Validated
@Transactional
public class ValidationService {

    private final BannedCardRepository cardRepository;
    private final BannedIpRepository ipRepository;

    public ValidationService(BannedCardRepository cardRepository, BannedIpRepository ipRepository) {
        this.cardRepository = cardRepository;
        this.ipRepository = ipRepository;
    }

    enum Reason {
        AMOUNT, CARD, IP;

        public String getReason() {
            return switch (this) {
                case AMOUNT -> "amount";
                case CARD -> "card-status";
                case IP -> "ip";
            };
        }
    }

    public Result validateTransaction(Transaction transaction) {
        Predicate<Transaction> isValidAmount = t -> t.amount() <= 1500;
        Predicate<Transaction> isValidCard = t -> !cardRepository.existsByNumber(t.status());
        Predicate<Transaction> isValidIp = t -> !ipRepository.existsByIp(t.ip());

        if (!isValidCard.test(transaction) || !isValidIp.test(transaction)) {
            var list = Map.of(AMOUNT, isValidAmount, CARD, isValidCard, IP, isValidIp)
                    .entrySet().stream()
                    .filter(e -> !e.getValue().test(transaction))
                    .map(Map.Entry::getKey)
                    .map(Reason::getReason).toList();
            return new Result(PROHIBITED, list);
        }

        return switch (transaction) {
            case Transaction t when t.amount() <= 200 -> new Result(ALLOWED);
            case Transaction t when t.amount() <= 1500 -> new Result(MANUAL_PROCESSING, AMOUNT.getReason());
            default -> new Result(PROHIBITED, AMOUNT.getReason());
        };
    }

    public BannedIP saveSusIp(SusIpRequest request) {
        if (ipRepository.existsByIp(request.ip())) {
            throw new ConflictException("IP already exists");
        }
        return ipRepository.save(new BannedIP(request));
    }

    public List<SusIpResponse> listSusIps() {
        return ipRepository.findAll().stream().map(SusIpResponse::new).toList();
    }

    public void deleteSusIp(String ip) {
        new SusIpRequest(ip);
        if (!ipRepository.existsByIp(ip)) {
            throw new NotFoundException("IP not found");
        }
        ipRepository.deleteByIp(ip);
    }

    public BannedCard saveStolenCard(CardRequest request) {
        if (cardRepository.existsByNumber(request.status())) {
            throw new ConflictException("Card already exists");
        }
        return cardRepository.save(new BannedCard(request));
    }

    public List<CardResponse> listStolenCards() {
        return cardRepository.findAll().stream().map(CardResponse::new).toList();
    }

    public void deleteStolenCard(@Valid CardRequest request) {
        if (!cardRepository.existsByNumber(request.status())) {
            throw new NotFoundException("Card not found");
        }
        cardRepository.deleteByNumber(request.status());
    }
}
