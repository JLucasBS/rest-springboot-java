package dev.jlucasbs.study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {

    public RequiredObjectIsNullException() {
        super("Person object must not be null");
    }

    public RequiredObjectIsNullException(String message) {
        super(message);
    }
}
