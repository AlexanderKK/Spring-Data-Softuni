package bg.softuni.workshop.controllers;

import bg.softuni.workshop.models.user.User;
import bg.softuni.workshop.models.user.dto.LoginDTO;
import bg.softuni.workshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/users/login")
    public String doLogin(LoginDTO loginDTO) {
        Optional<User> user = userService.login(loginDTO);

        if(user.isPresent()) {
            return "redirect:/home";
        }

        return "user/login";
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public String test() {
//        return null;
//    }

}
