package antifraud.controller;

import antifraud.entity.validation.*;
import antifraud.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/antifraud")
public class ValidationController {

    private final ValidationService validationService;

    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/transaction")
    public Result validateTransaction(@RequestBody @Valid Transaction transaction) {
        return validationService.validateTransaction(transaction);
    }

    @PostMapping("/suspicious-ip")
    public SusIpResponse saveSusIP(@RequestBody @Valid SusIpRequest request) {
        return new SusIpResponse(validationService.saveSusIp(request));
    }

    @GetMapping("/suspicious-ip")
    public List<SusIpResponse> listSusIps() {
        return validationService.listSusIps();
    }

    @DeleteMapping("/suspicious-ip/{ip}")
    public SusIpRemoved deleteSusIp(@PathVariable String ip) {
        validationService.deleteSusIp(ip);
        return new SusIpRemoved(ip);
    }

    @PostMapping("/stolencard")
    public CardResponse saveStolenCard(@RequestBody @Valid CardRequest request) {
        return new CardResponse(validationService.saveStolenCard(request));
    }

    @GetMapping("/stolencard")
    public List<CardResponse> getStolenCards() {
        return validationService.listStolenCards();
    }

    @DeleteMapping("/stolencard/{number}")
    public CardRemoved deleteStolenCard(@PathVariable String number) {
        validationService.deleteStolenCard(new CardRequest(number));
        return new CardRemoved(number);
    }
}
