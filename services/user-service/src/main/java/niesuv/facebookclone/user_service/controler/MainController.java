package niesuv.facebookclone.user_service.controler;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import niesuv.facebookclone.user_service.dto.CreateUserDTO;
import niesuv.facebookclone.user_service.dto.FacebookUserDto;
import niesuv.facebookclone.user_service.dto.UpdateUserDto;
import niesuv.facebookclone.user_service.entity.Friend;
import niesuv.facebookclone.user_service.service.FriendService;
import niesuv.facebookclone.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @GetMapping("")
    String hello() {
        return "Hello World";
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@Valid @RequestBody CreateUserDTO dto) {
        System.out.println(dto);
        return userService.createUser(dto);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public UUID updateUser(@RequestBody @Valid UpdateUserDto dto) {
        return userService.updateUser(dto);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/setfriend")
    public void setFriend(@RequestBody @Valid Friend fr) {
        System.out.println(fr.getUser1Id());
        System.out.println(fr.getUser2Id());

        friendService.setFriend(fr.getUser1Id(), fr.getUser2Id());
    }

    @GetMapping("/getfriend/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<FacebookUserDto> getFriend(@PathVariable("id") UUID id
            , @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size) {
        return friendService.getFriend(id, PageRequest.of(page, size));
    }
}
