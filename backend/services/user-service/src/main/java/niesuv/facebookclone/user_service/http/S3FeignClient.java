package niesuv.facebookclone.user_service.http;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "S3-SERVICE")
public interface S3FeignClient {

    @DeleteMapping("/deletefolder")
    ResponseEntity<Object> deleteFolder(@RequestParam("folder") String folder);

    @DeleteMapping("")
    ResponseEntity<Object> deleteFile(@RequestParam("key") String key);


}
