package niesuv.facebookclone.user_service.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link niesuv.facebookclone.user_service.entity.FacebookUser}
 */
public record UpdateUserDto(@NotNull UUID id, String fullName, String avtUrl,
                            String backgroundUrl){}