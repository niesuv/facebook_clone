package niesuv.facebookclone.s3_service.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.io.InputStream;

import static niesuv.facebookclone.s3_service.constants.Constants.BUCKET_NAME;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Autowired
    private S3Client s3Client;

    public boolean uploadFile(MultipartFile file,String key) {
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
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(BUCKET_NAME)
                    .key(folderName).build();
            DeleteObjectResponse response = s3Client.deleteObject(deleteObjectRequest);
            return response.sdkHttpResponse().isSuccessful();

        } catch (AwsServiceException | SdkClientException e) {
            throw new RuntimeException(e);
        }
    }

}
