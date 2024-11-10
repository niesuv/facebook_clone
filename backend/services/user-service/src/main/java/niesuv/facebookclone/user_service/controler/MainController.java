package niesuv.facebookclone.user_service.controler;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import niesuv.facebookclone.user_service.dto.CreateUserDTO;
import niesuv.facebookclone.user_service.dto.FacebookUserDto;
import niesuv.facebookclone.user_service.dto.UpdateUserDto;
import niesuv.facebookclone.user_service.service.FriendService;
import niesuv.facebookclone.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@NoArgsConstructor
@Setter
@Getter
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;


    @Value("${current_profile}")
    private String current_profile;

    @GetMapping("")
    public String hello() {
        return current_profile;
    }

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
    public void deleteUser(@PathVariable("userId") UUID id) {
        userService.deleteUser(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/exists/{userId}")
    public boolean exists(@PathVariable("userId") UUID id) {
        return userService.existsById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public FacebookUserDto getUser(@PathVariable("userId") UUID id) {
        return userService.getUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public FacebookUserDto getUser(@RequestParam("username") String username) {
        return userService.getUserByUserName(username);
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
