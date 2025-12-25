package antifraud.entity.validation;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.CreditCardNumber;

public record CardRequest(@NotEmpty @CreditCardNumber String status) {}
