package niesuv.facebookclone.user_service.exception;

public class CannotSaveUser extends RuntimeException {
    public CannotSaveUser(String msg) {
        super(msg);
    }
}
