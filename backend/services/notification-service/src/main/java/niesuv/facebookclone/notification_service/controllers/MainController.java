package niesuv.facebookclone.notification_service.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class MainController {
    @GetMapping("")
    String hello() {
        return "Hello World";
    }


}
