package niesuv.facebookclone.post_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link niesuv.facebookclone.post_service.entity.Comment}
 */

@Valid
public record CreateCommentDTO(UUID replyToId, @NotNull UUID postId
        ,@NotNull @NotBlank String content, @NotNull UUID userId) implements Serializable {
}