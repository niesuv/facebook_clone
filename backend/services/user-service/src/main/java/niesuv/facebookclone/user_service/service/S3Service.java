package niesuv.facebookclone.user_service.service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import niesuv.facebookclone.user_service.http.S3FeignClient;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@EnableAsync
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    @Autowired
    private S3FeignClient s3FeignClient;

    @Async
    public void deleteFolder(String key) {
        s3FeignClient.deleteFolder(key);
        log.info("DELETE FOLDER: {}", key);
    }


}
