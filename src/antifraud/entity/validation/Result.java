package antifraud.entity.validation;

import java.util.List;

public record Result(String result, String info) {
    public enum OPTION {
        ALLOWED, PROHIBITED, MANUAL_PROCESSING
    }

    public Result(OPTION option) {
        this(option.name(), "none");
    }

    public Result(OPTION option, String info) {
        this(option.name(), info);
    }

    public Result(OPTION option, List<String> reasons) {
        this(option.name(), reasons.isEmpty() ? "none" :
                String.join(", ", reasons.stream()
                        .sorted(String::compareTo)
                        .toList()));
    }
}
