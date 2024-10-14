package niesuv.facebookclone.post_service.repository;

import niesuv.facebookclone.post_service.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query("select c from Comment c where c.userId = ?1")
    List<Comment> findAllByUserID(UUID userID);
}