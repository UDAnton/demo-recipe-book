package com.github.udanton.demorecipebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String messages) {
        super(messages);
    }

    public NotFoundException(String messages, Throwable throwable) {
        super(messages, throwable);
    }

}
