package antifraud.entity.validation;

public record SusIpRemoved(String status) {
    public SusIpRemoved {
        status = "IP %s successfully removed!".formatted(status);
    }
}
