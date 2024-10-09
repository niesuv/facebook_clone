package niesuv.facebookclone.post_service.hanlder;

import niesuv.facebookclone.post_service.exception.CannotSaveImageException;
import niesuv.facebookclone.post_service.exception.CreatePostException;
import niesuv.facebookclone.post_service.exception.NotImageOrVideoException;
import niesuv.facebookclone.post_service.exception.PostIdNotExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({CreatePostException.class, CannotSaveImageException.class,
    PostIdNotExists.class, NotImageOrVideoException.class})
    public String handleValidationExceptions(Exception e) {
        return e.getMessage();
    }

}
