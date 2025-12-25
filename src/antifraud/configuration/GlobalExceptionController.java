package antifraud.configuration;

import antifraud.exception.ConflictException;
import antifraud.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflictException(ConflictException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("[DEBUG_LOG] MethodArgumentNotValidException");
        return ResponseEntity.status(400).body("Bad Request");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        System.out.println("[DEBUG_LOG] IllegalArgumentException");
        return ResponseEntity.status(400).body("Bad Request");
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException e) {
        System.out.println("[DEBUG_LOG] HttpMessageNotReadableException");
        return ResponseEntity.status(400).body("Bad Request");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception e) {
        System.out.println("[DEBUG_LOG] Exception: " + e.getClass().getName());
        return ResponseEntity.status(400).body("Bad Request");
    }
}
