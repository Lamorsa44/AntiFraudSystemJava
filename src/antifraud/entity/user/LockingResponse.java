package antifraud.entity.user;

public record LockingResponse(String status) {
    public LockingResponse(MaiUser maiUser) {
        this("User %s %s!".formatted(maiUser.getUsername(),
                maiUser.getLock().isLock() ? "locked" : "unlocked"));
    }
}
