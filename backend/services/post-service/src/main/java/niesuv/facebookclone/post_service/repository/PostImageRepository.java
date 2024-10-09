package niesuv.facebookclone.post_service.repository;

import niesuv.facebookclone.post_service.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostImageRepository extends JpaRepository<PostImage, UUID> {
}