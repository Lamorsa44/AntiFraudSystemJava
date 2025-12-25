package antifraud.entity.user;

public enum Lock {
    LOCK, UNLOCK;

    public boolean isLock() {
        return this == LOCK;
    }
}
