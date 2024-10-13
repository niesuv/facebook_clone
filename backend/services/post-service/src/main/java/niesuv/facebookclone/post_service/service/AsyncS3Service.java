package niesuv.facebookclone.post_service.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import niesuv.facebookclone.post_service.http.S3FeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AsyncS3Service {

    @Autowired
    private final S3FeignClient s3FeignClient;

    @Async
    public void deleteFolder(String folder) {
        var res = s3FeignClient.deleteFolder(folder);
        if (res.getStatusCode().is2xxSuccessful())
            log.info("Successfully deleted folder {}", folder);
        else
            log.error("Failed to delete folder {}", folder);
    }
}
