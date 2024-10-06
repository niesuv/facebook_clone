package niesuv.facebookclone.user_service.exception;

import lombok.Data;

@Data
public class CreateUserException extends RuntimeException {
    String msg;
    public CreateUserException(String msg) {
        super(msg);
    }
}
