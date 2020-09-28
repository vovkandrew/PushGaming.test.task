package pushgaming.test.task.demo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pushgaming.test.task.demo.model.ApiError;
import pushgaming.test.task.demo.exception.StakeException;

@ControllerAdvice
public class StakeExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(StakeException.class)
    protected ResponseEntity<Object> handleBalanceException(StakeException ex) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }
}
