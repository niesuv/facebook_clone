package niesuv.facebookclone.user_service.repository;

import niesuv.facebookclone.user_service.entity.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface FacebookUserRepository extends JpaRepository<FacebookUser, UUID> {

    @Query("select (count(f) > 0) from FacebookUser f where f.email = ?1")
    boolean existsByEmail(String email);

    @Query("select (count(f) > 0) from FacebookUser f where f.userName = ?1")
    boolean existsByUserName(String userName);

    @Query("select u from FacebookUser u left join fetch u.friends where u.id = ?1")
    FacebookUser getUserWithAllFriends(UUID id);

    @Modifying
    @Query("update FacebookUser u set u.totalFriends = (select count(f) from Friend f where f.friendId.user1Id = ?1) " +
            "where u.id = ?1")
    void updateNumberOfFriends(UUID uuid);

    @Override
    boolean existsById(UUID uuid);

}
