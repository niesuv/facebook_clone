package niesuv.facebookclone.user_service;

import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.user_service.entity.FacebookUser;
import niesuv.facebookclone.user_service.repository.FacebookUserRepository;
import niesuv.facebookclone.user_service.repository.FriendRepository;
import niesuv.facebookclone.user_service.service.FriendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class UserServiceApplicationTests {

    @Autowired
    FacebookUserRepository userRepository;

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    FriendService friendService;

    @Test
    void addFriend() {
        FacebookUser user1 = FacebookUser.builder().userName("niesuv")
                .email("sang@gmail.com").fullName("Le huu Snag").build();
        FacebookUser user2 = FacebookUser.builder().userName("niesuv2")
                .email("sang2@gmail.com").fullName("Le huu Snag2").build();

        userRepository.saveAll(List.of(user1, user2));
        friendService.addFriend(user1.getId(), user2.getId());


    }

    @Test
    void deleteFriend() {
        FacebookUser user1 = FacebookUser.builder().userName("niesuv")
                .email("sang@gmail.com").fullName("Le huu Snag").build();
        FacebookUser user2 = FacebookUser.builder().userName("niesuv2")
                .email("sang2@gmail.com").fullName("Le huu Snag2").build();

        userRepository.saveAll(List.of(user1, user2));
        friendService.addFriend(user1.getId(), user2.getId());
        userRepository.delete(user1);
    }


    @Test
    void deleteFriendViaFriend() {
        FacebookUser user1 = FacebookUser.builder().userName("niesuv")
                .email("sang@gmail.com").fullName("Le huu Snag").build();
        FacebookUser user2 = FacebookUser.builder().userName("niesuv2")
                .email("sang2@gmail.com").fullName("Le huu Snag2").build();

        userRepository.saveAll(List.of(user1, user2));
        friendService.addFriend(user1.getId(), user2.getId());
        friendRepository.deleteFriendBy2UserId(user1.getId(), user2.getId());

    }
}
