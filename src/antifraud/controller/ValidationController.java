package antifraud.controller;

import antifraud.entity.validation.Result;
import antifraud.entity.validation.Transaction;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static antifraud.entity.validation.Result.OPTION.*;

@RestController
@RequestMapping("/api/antifraud")
public class ValidationController {

    @PostMapping("/transaction")
    public Result validateTransaction(@RequestBody @Valid Transaction transaction) {
        return switch (transaction) {
            case Transaction(long amount) when amount <= 200 -> new Result(ALLOWED);
            case Transaction(long amount) when amount <= 1500 -> new Result(MANUAL_PROCESSING);
            default -> new Result(PROHIBITED);
        };
    }
}
