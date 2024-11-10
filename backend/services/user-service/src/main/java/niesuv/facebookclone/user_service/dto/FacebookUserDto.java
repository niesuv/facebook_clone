package niesuv.facebookclone.user_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link niesuv.facebookclone.user_service.entity.FacebookUser}
 */

@Builder
public record FacebookUserDto(@NotNull UUID id, String userName, String fullName, String avtUrl, String email,
                              String backgroundUrl, LocalDate birthday) implements Serializable {
}