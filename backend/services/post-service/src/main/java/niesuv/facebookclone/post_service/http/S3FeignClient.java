package niesuv.facebookclone.post_service.http;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "s3-service")
public interface S3FeignClient {

    @PutMapping(value = "/upload")
    ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file
            , @RequestParam("key") String key);

    @GetMapping("")
    Object listBucket();


}
