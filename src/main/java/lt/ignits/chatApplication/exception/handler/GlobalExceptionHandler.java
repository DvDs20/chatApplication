package lt.ignits.chatApplication.exception.handler;

import lt.ignits.chatApplication.exception.UserAlreadyExistsException;
import lt.ignits.chatApplication.exception.UserNotFoundWithSpecifiedIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({UserNotFoundWithSpecifiedIdException.class})
    public ResponseEntity<Object> handleUserNotFoundWithSpecifiedIdException(UserNotFoundWithSpecifiedIdException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
