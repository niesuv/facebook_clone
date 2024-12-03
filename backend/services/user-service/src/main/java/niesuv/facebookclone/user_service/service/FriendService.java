package niesuv.facebookclone.user_service.service;


import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.user_service.entity.FacebookUser;
import niesuv.facebookclone.user_service.entity.Friend;
import niesuv.facebookclone.user_service.entity.FriendId;
import niesuv.facebookclone.user_service.exception.AlreadyFriendException;
import niesuv.facebookclone.user_service.exception.UserIdNotExists;
import niesuv.facebookclone.user_service.repository.FacebookUserRepository;
import niesuv.facebookclone.user_service.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FacebookUserRepository userRepository;


    public void addFriend(UUID user1, UUID user2) {
        if (!userRepository.existsById(user1) || !userRepository.existsById(user2))
            throw new UserIdNotExists("user id not exists");
        if (friendRepository.isFriend(user1, user2))
            throw new AlreadyFriendException("Already Friend!");

        FriendId id1 = new FriendId(user1, user2);
        FriendId id2 = new FriendId(user2, user1);


        friendRepository.saveAll(List.of(Friend.builder().friendId(id1).build()
                , Friend.builder().friendId(id2).build()));

        //call async
        updateTotalFriend(user1, 1);
        updateTotalFriend(user2, 1);
    }



    @Async
    public void updateTotalFriend(UUID id, int amount) {
        FacebookUser user = userRepository.findById(id).orElseThrow(
                () -> new UserIdNotExists("Error")
        );
        user.setTotalFriends(user.getTotalFriends() + amount);
        userRepository.save(user);
    }

    @Async
    public void updateTotalFriend(Set<FacebookUser> users, int amount) {
        users.forEach(u -> {
            u.setTotalFriends(u.getTotalFriends() + amount);
        });
        userRepository.saveAll(users);
    }



    public void deleteFriend(UUID id, UUID friendId) {
        if (!userRepository.existsById(id) || !userRepository.existsById(friendId))
            throw new UserIdNotExists("user id not exists");
        friendRepository.deleteFriendBy2UserId(id, friendId);
        updateTotalFriend(friendId, -1);
        updateTotalFriend(id, -1);
    }

}
