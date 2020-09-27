package spring.expert.exception;

import lombok.Builder;
import lombok.Data;

@Data
public class ResourceNotFoundDetails extends ErrorDetails{
    @Builder
    public ResourceNotFoundDetails(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title,status,detail,timestamp,developerMessage);
    }
}
