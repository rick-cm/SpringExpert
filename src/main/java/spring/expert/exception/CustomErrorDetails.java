package spring.expert.exception;

import lombok.Builder;
import lombok.Data;

@Data
public class CustomErrorDetails extends ErrorDetails{
    @Builder
   public CustomErrorDetails(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title, status, detail, timestamp, developerMessage);
    }
}
