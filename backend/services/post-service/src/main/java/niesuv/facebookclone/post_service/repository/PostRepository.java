package niesuv.facebookclone.post_service.repository;

import niesuv.facebookclone.post_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("select p from Post p join fetch p.images where p.id = ?1")
    Optional<Post> postById(UUID id);


    @Override
    void deleteById(UUID uuid);
}