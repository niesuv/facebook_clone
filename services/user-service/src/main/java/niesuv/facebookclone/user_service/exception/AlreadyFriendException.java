package niesuv.facebookclone.user_service.exception;

public class AlreadyFriendException extends RuntimeException {
    public AlreadyFriendException(String alreadyFriend) {
        super(alreadyFriend);
    }
}
