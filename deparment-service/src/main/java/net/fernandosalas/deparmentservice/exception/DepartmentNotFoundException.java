package net.fernandosalas.deparmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends RuntimeException {
    private String message;

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
