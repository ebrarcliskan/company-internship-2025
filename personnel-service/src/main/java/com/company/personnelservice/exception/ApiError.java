package com.company.personnelservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    public Instant timestamp = Instant.now();
    public int status;
    public String error;
    public String message;
    public String path;
    public List<FieldValidationError> errors;

    public ApiError(int status, String error, String message, String path, List<FieldValidationError> errors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    public static class FieldValidationError {
        public String field;
        public String message;

        public FieldValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}

