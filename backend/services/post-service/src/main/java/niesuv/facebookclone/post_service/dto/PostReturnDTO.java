package niesuv.facebookclone.post_service.dto;

import lombok.Builder;
import niesuv.facebookclone.post_service.entity.PostImage;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link niesuv.facebookclone.post_service.entity.Post}
 */

@Builder
public record PostReturnDTO(UUID id, String content, UUID userId, int totalLike, int shares, int totalComments,
                            LocalDateTime createTime, Set<PostImage> images) implements Serializable {
}