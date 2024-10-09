package niesuv.facebookclone.post_service.dto;

import jakarta.validation.constraints.NotNull;
import niesuv.facebookclone.post_service.entity.Like;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Like}
 */
public record CreateLikeDTO(UUID postId, UUID commentId, @NotNull UUID userId) implements Serializable {
}