package com.vikku.productservice.core.errorhandling;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {

    private final Date timestamp;
    private final String message;

    public ErrorMessage(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
