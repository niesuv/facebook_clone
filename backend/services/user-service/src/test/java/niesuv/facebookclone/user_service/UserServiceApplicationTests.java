package niesuv.facebookclone.user_service;

import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.user_service.repository.FacebookUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class UserServiceApplicationTests {

    @Autowired
    FacebookUserRepository userRepository;


    @Test
    void contextLoads() {
        ;
    }

}
