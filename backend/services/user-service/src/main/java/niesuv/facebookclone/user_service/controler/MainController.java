package niesuv.facebookclone.user_service.controler;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import niesuv.facebookclone.user_service.dto.CreateUserDTO;
import niesuv.facebookclone.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class MainController {

    @Autowired
    private UserService userService;


    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("")
    public UUID createUser(@RequestBody @Valid CreateUserDTO dto) {
        return userService.createUser(dto);
    }
}
