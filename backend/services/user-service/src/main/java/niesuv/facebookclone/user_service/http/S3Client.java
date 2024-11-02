package niesuv.facebookclone.user_service.http;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;


import java.io.IOException;

@Service
@Getter
@Setter
public class S3Client {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${s3-service}")
    private  String S3_URL;

    public void upload(MultipartFile file, String key) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource
                (file.getInputStream(), file.getOriginalFilename(), file.getSize()));
        body.add("key", key);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        String serverUrl = S3_URL + "/api/v1/s3/upload";

        restTemplate.put(serverUrl, request);
    }

}
