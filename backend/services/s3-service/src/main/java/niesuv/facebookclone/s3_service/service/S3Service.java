package niesuv.facebookclone.s3_service.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static niesuv.facebookclone.s3_service.constants.Constants.BUCKET_NAME;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Autowired
    private S3Client s3Client;

    public boolean uploadFile(MultipartFile file, String key) {
        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(BUCKET_NAME)
                    .key(key).build();
            PutObjectResponse response =
                    s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));
            return response.sdkHttpResponse().isSuccessful();
        } catch (IOException | AwsServiceException | SdkClientException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteFolder(String folderName) {
        try {
            ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                    .bucket(BUCKET_NAME)
                    .prefix(folderName)
                    .build();

            ListObjectsV2Response response = s3Client.listObjectsV2(listObjectsRequest);
            List<S3Object> items = response.contents();
            items.forEach((s) -> {
                deleteFile(s.key());
            });
            return true;
        } catch (AwsServiceException | SdkClientException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteFile(String key) {
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(BUCKET_NAME)
                    .key(key).build();
            DeleteObjectResponse response = s3Client.deleteObject(deleteObjectRequest);
            return response.sdkHttpResponse().isSuccessful();

        } catch (AwsServiceException | SdkClientException e) {
            throw new RuntimeException(e);
        }
    }
}
