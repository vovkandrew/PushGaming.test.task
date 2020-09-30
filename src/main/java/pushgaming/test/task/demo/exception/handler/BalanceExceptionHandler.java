package pushgaming.test.task.demo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pushgaming.test.task.demo.model.ApiError;
import pushgaming.test.task.demo.exception.BalanceException;

@ControllerAdvice
public class BalanceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BalanceException.class)
    protected ResponseEntity<Object> handleBalanceException(BalanceException ex) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.FORBIDDEN);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }
}
