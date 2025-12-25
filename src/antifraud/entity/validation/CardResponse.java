package antifraud.entity.validation;

public record CardResponse(Long id, String status) {
    public CardResponse(BannedCard bannedCard) {
        this(bannedCard.getId(), bannedCard.getNumber());
    }
}
