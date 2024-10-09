package niesuv.facebookclone.post_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link niesuv.facebookclone.post_service.entity.Post}
 */


@Valid
public record CreatePostDto(@NotNull @NotBlank String content, UUID shareId, @NotNull UUID userId) implements Serializable {
}