package niesuv.facebookclone.s3_service.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class s3config {

    @Value("${aws_access_key_id}")
    private  String keyId;

    @Value("${aws_secret_access_key}")
    private  String accessKey;

    @Bean
    public S3Client s3Client() {
        System.out.println(keyId);
        StaticCredentialsProvider provider =
                StaticCredentialsProvider.create(AwsBasicCredentials.create(keyId, accessKey));

        return S3Client.builder()
                .httpClientBuilder(ApacheHttpClient.builder())
                .credentialsProvider(provider)
                .region(Region.AP_SOUTHEAST_1)
                .build();
    }

}
