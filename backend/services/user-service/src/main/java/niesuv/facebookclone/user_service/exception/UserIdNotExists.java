package niesuv.facebookclone.user_service.exception;

public class UserIdNotExists extends RuntimeException {
    public UserIdNotExists(String msg) {
        super(msg);
    }
}
