package niesuv.facebookclone.user_service.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import niesuv.facebookclone.user_service.dto.CreateUserDTO;
import niesuv.facebookclone.user_service.dto.FacebookUserDto;
import niesuv.facebookclone.user_service.entity.FacebookUser;
import niesuv.facebookclone.user_service.entity.Friend;
import niesuv.facebookclone.user_service.exception.AlreadyFriendException;
import niesuv.facebookclone.user_service.exception.UserIdDontExists;
import niesuv.facebookclone.user_service.mapper.UserMapper;
import niesuv.facebookclone.user_service.repository.FriendRepository;
import niesuv.facebookclone.user_service.repository.UserRepository;
import org.apache.catalina.User;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound1Payload;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {


    @Autowired
    private UserService userService;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    public void setFriend(UUID p1, UUID p2) {
        if (userRepository.existsById(p1) && userRepository.existsById(p2)) {
            if (friendRepository.isFriend(p1, p2))
                throw new AlreadyFriendException("Already friend");
            Friend f1 = new Friend(0L,p1, p2);
            Friend f2 = new Friend(0L,p2, p1);
            friendRepository.saveAll(List.of(f1, f2));
            return;
        }
        throw new UserIdDontExists("User id does not exist");
    }

    public List<FacebookUserDto> getFriend(UUID id, Pageable pageable) {
        if (userRepository.existsById(id)) {
            List<Friend> fr;
            if (pageable == null)
                fr = friendRepository.getFriendByUser1Id(id);
            else fr = friendRepository.getFriendByUser1Id(id, pageable);
            List<UUID> listFr = fr.stream().map(Friend::getUser2Id).toList();
            return userRepository.findAllByIdIn(listFr).stream().map( u -> userMapper.toFaceBookUserDto(u)).toList();
        }

        throw new UserIdDontExists("user id dont exists");
    }
}
