package antifraud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String username) {
        super("User with username " + username + " not found");
    }

    public NotFoundException(long id) {
        super("User with id " + id + " not found");
    }
}
