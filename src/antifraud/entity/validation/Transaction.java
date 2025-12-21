package antifraud.entity.validation;

import jakarta.validation.constraints.Min;

public record Transaction(@Min(1) long amount) {}
