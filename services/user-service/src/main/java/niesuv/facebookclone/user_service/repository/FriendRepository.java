package niesuv.facebookclone.user_service.repository;

import niesuv.facebookclone.user_service.entity.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {


    @Query("select (count(f) > 0) from Friend f where f.user1Id = ?1 and f.user2Id = ?2")
    boolean isFriend(UUID user1Id, UUID user2Id);

    @Transactional
    @Modifying
    @Query("delete from Friend f where (f.user1Id = ?1 and f.user2Id = ?2) or (f.user1Id = ?2 and f.user2Id = ?1)")
    int deleteFriend(UUID user1Id, UUID user2Id);

    @Query("select count(f) from Friend  f where f.user1Id = ?1")
    int friendCount(UUID userId);


    List<Friend> getFriendByUser1Id(UUID userId1, Pageable pageable);

    List<Friend> getFriendByUser1Id(UUID userId1);
}