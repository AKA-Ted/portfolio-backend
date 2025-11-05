package com.site.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceConflictException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("The resource '%s' already exists %s = '%s'",
                resourceName, fieldName, fieldValue));
    }
    public ResourceConflictException(String message) {
        super(message);
    }
}
