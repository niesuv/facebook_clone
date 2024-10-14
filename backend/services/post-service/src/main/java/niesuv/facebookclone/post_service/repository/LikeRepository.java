package niesuv.facebookclone.post_service.repository;

import niesuv.facebookclone.post_service.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {


    @Query("select l from Like l where l.userId = ?1")
    List<Like> findAllByUserId(UUID userId);
}