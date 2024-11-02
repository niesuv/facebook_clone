package niesuv.facebookclone.post_service.repository;

import niesuv.facebookclone.post_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("select p from Post p left join fetch p.images where p.id = ?1")
    Optional<Post> postById(UUID id);

    @Query("select p from Post p where p.userId = ?1")
    List<Post> findAllByUserId(UUID userId);


    @Override
    void deleteById(UUID uuid);
}