package antifraud.entity.validation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.CreditCardNumber;

public record Transaction(@Min(1) long amount,
                          @NotEmpty String ip,
                          @CreditCardNumber String status) {}
