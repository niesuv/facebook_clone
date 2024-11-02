package niesuv.facebookclone.user_service.http;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "S3-SERVICE", url = "${s3-service}")
public interface S3FeignClient {

    @DeleteMapping("/api/v1/s3/deletefolder")
    ResponseEntity<String> deleteFolder(@RequestParam("folder") String folder);

    @DeleteMapping("/api/v1/s3")
    ResponseEntity<String> deleteFile(@RequestParam("key") String key);

}
