package lt.ignits.chatApplication.exception;

public class UserNotFoundWithSpecifiedIdException extends RuntimeException{
    public UserNotFoundWithSpecifiedIdException(String message){
        super(message);
    }
}
