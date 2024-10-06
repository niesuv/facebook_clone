package niesuv.facebookclone.user_service.exception;


import lombok.Data;

@Data
public class UserNameExistException extends RuntimeException {
    String msg;
    public UserNameExistException(String msg) {
        super(msg);
    }
}
