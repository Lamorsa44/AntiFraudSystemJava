package antifraud.entity.validation;

public record CardRemoved(String status) {
    public CardRemoved {
        status = "Card %s successfully removed!".formatted(status);
    }
}
