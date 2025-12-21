package antifraud.entity.validation;

public record Result(String result) {
    public enum OPTION {
        ALLOWED, PROHIBITED, MANUAL_PROCESSING
    }

    public Result(OPTION option) {
        this(option.name());
    }
}
