package niesuv.facebookclone.user_service.repository;

import niesuv.facebookclone.user_service.entity.Friend;
import niesuv.facebookclone.user_service.entity.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {


    @Query("select (count(f) > 0) from Friend f where (f.friendId.user1Id = ?1 and f.friendId.user2Id = ?2)" +
            "or (f.friendId.user1Id = ?2 and f.friendId.user2Id = ?1)")
    boolean isFriend(UUID user1, UUID user2);

//    @Transactional
//    @Modifying
//    @Query("delete from Friend f where f.friendId = ?1")
//    int deleteByFriendId(FriendId friendId);

    @Transactional
    @Modifying
    @Query("delete  from Friend f where (f.friendId.user1Id = ?1 and f.friendId.user2Id = ?2)" +
            "or (f.friendId.user1Id = ?2 and f.friendId.user2Id = ?1)" )
    void deleteFriendBy2UserId(UUID user1Id, UUID user2ID);

}