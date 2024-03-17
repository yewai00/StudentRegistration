package com.yewai.StudentRegistration.exceptions.handler;

import java.io.Serial;
import java.util.Map;

public class EmailDuplicateException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private Map<String, String> details;

    public EmailDuplicateException(String message, Map<String, String> details) {
        super(message);
        this.details = details;
    }

    public Map<String, String> getDetails() {
        return this.details;
    }
}
