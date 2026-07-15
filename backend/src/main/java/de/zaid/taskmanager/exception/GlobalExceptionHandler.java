package de.zaid.taskmanager.exception;

import de.zaid.taskmanager.dto.ApiError;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// Wandelt Fehler in verständliche HTTP-Antworten um.
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    // Eine unbekannte Aufgaben-ID führt zu 404 Not Found.
    public ResponseEntity<ApiError> handleTaskNotFound(TaskNotFoundException exception) {
        return createError(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Ungültige Pflichtfelder führen zu 400 Bad Request.
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Request data is invalid");

        return createError(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    // Ein nicht lesbarer JSON-Body führt ebenfalls zu 400 Bad Request.
    public ResponseEntity<ApiError> handleUnreadableRequest(HttpMessageNotReadableException exception) {
        return createError(HttpStatus.BAD_REQUEST, "Request body contains invalid data");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    // Behandelt zum Beispiel einen unbekannten Sortierwert.
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException exception) {
        return createError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    // Erstellt aus Status und Nachricht die gemeinsame Fehlerantwort.
    private ResponseEntity<ApiError> createError(HttpStatus status, String message) {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message);

        return ResponseEntity.status(status).body(error);
    }
}
