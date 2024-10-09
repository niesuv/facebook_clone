package niesuv.facebookclone.user_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import niesuv.facebookclone.user_service.annotations.NullOrNotBlank;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link niesuv.facebookclone.user_service.entity.FacebookUser}
 */
@Valid
public record UpdateUserDto(@NotNull UUID id, @NullOrNotBlank String userName, @Email @NullOrNotBlank String email, @NullOrNotBlank String fullName) implements Serializable {
}