package niesuv.facebookclone.user_service.exception;

public class UserIdDontExists extends RuntimeException {
    public UserIdDontExists(String msg) {
        super(msg);
    }
}
