package niesuv.facebookclone.user_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link niesuv.facebookclone.user_service.entity.FacebookUser}
 */

@Valid
public record CreateUserDTO(@NotNull String userName, @NotNull @NotBlank @Email String email,
                            @NotNull String fullName) implements Serializable {
}
