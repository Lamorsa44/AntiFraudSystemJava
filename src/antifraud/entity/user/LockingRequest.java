package antifraud.entity.user;

public record LockingRequest(String username, String operation) {
    public LockingRequest {
        if (!operation.equals("LOCK") && !operation.equals("UNLOCK"))
            throw new IllegalArgumentException();
    }

    public Lock getOperation() {
        return Lock.valueOf(operation);
    }
}
