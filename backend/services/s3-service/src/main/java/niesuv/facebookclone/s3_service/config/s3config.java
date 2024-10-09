package niesuv.facebookclone.s3_service.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@RequiredArgsConstructor
public class s3config {

    @Autowired
    Environment environment;

    @Bean
    public S3Client s3Client() {

        StaticCredentialsProvider provider =
                StaticCredentialsProvider.create(AwsBasicCredentials.create(environment.getProperty("aws_access_key")
                        , environment.getProperty("aws_secret_key")));

        return S3Client.builder()
                .httpClientBuilder(ApacheHttpClient.builder())
                .credentialsProvider(provider)
                .region(Region.AP_SOUTHEAST_1)
                .build();
    }

}
