package niesuv.facebookclone.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link niesuv.facebookclone.user_service.entity.FacebookUser}
 */
public record CreateUserDTO(@NotNull String userName, @NotNull @Email String email,
                            @NotNull String fullName) implements Serializable {



}