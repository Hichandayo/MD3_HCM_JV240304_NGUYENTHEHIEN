package exception;

public class UsernameAndPasswordException extends RuntimeException{
    public UsernameAndPasswordException(String message) {
        super(message);
    }
}
