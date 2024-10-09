package niesuv.facebookclone.post_service.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String s) {
        super(s);
    }
}
