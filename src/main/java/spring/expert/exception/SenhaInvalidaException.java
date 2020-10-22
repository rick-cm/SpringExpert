package spring.expert.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(String message){
        super(message);
    }
}
