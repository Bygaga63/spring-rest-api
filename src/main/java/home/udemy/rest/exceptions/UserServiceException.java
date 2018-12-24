package home.udemy.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserServiceException extends RuntimeException{
    public UserServiceException() {
    }

    public UserServiceException(String message) {
        super(message);
    }
}
