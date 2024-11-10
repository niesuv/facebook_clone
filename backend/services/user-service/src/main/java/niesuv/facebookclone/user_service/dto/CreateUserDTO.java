package niesuv.facebookclone.user_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link niesuv.facebookclone.user_service.entity.FacebookUser}
 */

@Valid
public record CreateUserDTO(@NotNull String userName, @NotNull @NotBlank @Email String email,
                            @NotNull String fullName, @Past(message = "Birthday not valid") @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate birthDay) implements Serializable {
}
