package niesuv.facebookclone.post_service.exception;

public class PostIdNotExists extends RuntimeException {
    public PostIdNotExists(String s) {
        super(s);
    }
}
