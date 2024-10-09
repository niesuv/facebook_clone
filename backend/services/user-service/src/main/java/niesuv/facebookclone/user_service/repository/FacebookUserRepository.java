package niesuv.facebookclone.user_service.repository;

import niesuv.facebookclone.user_service.entity.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface FacebookUserRepository extends JpaRepository<FacebookUser, UUID> {

    @Query("select (count(f) > 0) from FacebookUser f where f.email = ?1")
    boolean existsByEmail(String email);

    @Query("select (count(f) > 0) from FacebookUser f where f.userName = ?1")
    boolean existsByUserName(String userName);


    FacebookUser getById(UUID id);

    @Override
    boolean existsById(UUID uuid);
}