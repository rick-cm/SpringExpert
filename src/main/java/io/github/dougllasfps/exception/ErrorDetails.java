package io.github.dougllasfps.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {

    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;

    public String getTitle() {
        return title;
    }
}
