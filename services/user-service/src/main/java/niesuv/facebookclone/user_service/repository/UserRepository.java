package niesuv.facebookclone.user_service.repository;

import niesuv.facebookclone.user_service.entity.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<FacebookUser, UUID> {

    boolean existsByUserName(String userName);

    List<FacebookUser> findAllByIdIn(List<UUID> id);

    @Override
    boolean existsById(UUID uuid);
}
