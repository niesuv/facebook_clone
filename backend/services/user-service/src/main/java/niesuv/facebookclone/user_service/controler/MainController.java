package niesuv.facebookclone.user_service.controler;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import niesuv.facebookclone.user_service.dto.CreateUserDTO;
import niesuv.facebookclone.user_service.dto.UpdateUserDto;
import niesuv.facebookclone.user_service.service.FriendService;
import niesuv.facebookclone.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("")
    public UUID createUser(@RequestBody @Valid CreateUserDTO dto) {
        return userService.createUser(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    public void updateUser(@RequestBody @Valid UpdateUserDto dto) {
        userService.updateUser(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{userId}")
    public void updateUser(@PathVariable("userId") UUID id) {
        userService.deleteUser(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/exists/{userId}")
    public boolean exists(@PathVariable("userId") UUID id) {
        return userService.existsById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/avatar")
    public void updateAvatar(@RequestParam("image") MultipartFile file
            , @RequestParam("userId") UUID id) {
        userService.updateAvatar(id, file);

    }

    @PostMapping("/background")
    public void updateBackGround(@RequestParam("image") MultipartFile file
            , @RequestParam("userId") UUID id) {
        userService.updateBackGround(id, file);

    }

    @PutMapping("/addfriend")
    public void addFriend(@RequestParam("userId") UUID id,
                          @RequestParam("friendId") UUID friendId) {
        friendService.addFriend(id, friendId);
    }

    @DeleteMapping("/deletefriend")
    public void deleleFriend(@RequestParam("userId") UUID id,
                             @RequestParam("friendId") UUID friendId) {
        friendService.deleteFriend(id, friendId);
    }

}
