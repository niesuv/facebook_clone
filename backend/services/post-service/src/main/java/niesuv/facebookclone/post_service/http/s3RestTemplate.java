package niesuv.facebookclone.post_service.http;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class s3RestTemplate {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    private final String S3_URL = "http://S3-SERVICE";
    public void upload(MultipartFile file, String key) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource
                (file.getInputStream(), file.getOriginalFilename(), file.getSize()));
        body.add("key", key);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        String serverUrl = S3_URL + "/upload";

        restTemplate.put(serverUrl, request);
    }
}


