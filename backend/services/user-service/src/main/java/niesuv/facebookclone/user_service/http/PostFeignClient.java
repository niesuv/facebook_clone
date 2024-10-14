package niesuv.facebookclone.user_service.http;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@FeignClient(name = "POST-SERVICE")
public interface PostFeignClient {

    @DeleteMapping("/api/v1/post")
    @Async
    @ResponseStatus(HttpStatus.OK)
    void clearActionsByUserId(@RequestParam("userId") UUID userId);

}
