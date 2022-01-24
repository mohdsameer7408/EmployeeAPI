package com.example.employee_api.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

public class ApiException {

    private final String type = "Api Exception";
    private String message;
    private HttpStatus httpStatus;
    private LocalDate timestamp;
    private String details;

    public ApiException(String message, HttpStatus httpStatus, LocalDate timestamp, String details) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ApiException [HttpStatus=" + httpStatus + ", message=" + message + ", details=" + details
                + ", timestamp=" + timestamp + ", type=" + type + "]";
    }

}
