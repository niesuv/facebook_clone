package niesuv.facebookclone.post_service.http;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "s3-SERVICE")
public interface S3FeignClient {


    @DeleteMapping("/deletefolder")
    ResponseEntity<String> deleteFolder(@RequestParam("folder") String folder);

    @DeleteMapping("")
    ResponseEntity<String> deleteFile(@RequestParam("key") String key);


}
