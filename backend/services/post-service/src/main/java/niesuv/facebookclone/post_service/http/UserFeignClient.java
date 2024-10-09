package niesuv.facebookclone.post_service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("api/v1/user/exists/{userId}")
    boolean exists(@PathVariable("userId") UUID id);


}
