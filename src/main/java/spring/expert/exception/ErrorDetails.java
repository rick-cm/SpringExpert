package spring.expert.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;
}
