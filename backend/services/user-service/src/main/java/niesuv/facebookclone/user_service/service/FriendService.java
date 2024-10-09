package niesuv.facebookclone.user_service.service;


import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.user_service.entity.Friend;
import niesuv.facebookclone.user_service.entity.FriendId;
import niesuv.facebookclone.user_service.exception.AlreadyFriendException;
import niesuv.facebookclone.user_service.repository.FacebookUserRepository;
import niesuv.facebookclone.user_service.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FacebookUserRepository userRepository;


    public boolean addFriend(UUID user1, UUID user2) {
        if (friendRepository.isFriend(user1, user2))
            throw new AlreadyFriendException("Already Friend!");

        FriendId id1 = new FriendId(user1, user2);
        FriendId id2 = new FriendId(user2, user1);


        friendRepository.saveAll(List.of(Friend.builder().friendId(id1).build()
                , Friend.builder().friendId(id2).build()));
        return true;
    }


}
