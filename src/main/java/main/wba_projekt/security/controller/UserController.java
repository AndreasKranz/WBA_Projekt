package main.wba_projekt.security.controller;

import lombok.NoArgsConstructor;
import main.wba_projekt.security.model.User;
import main.wba_projekt.security.repository.UserRepository;
import main.wba_projekt.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@NoArgsConstructor
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserService userService;

    @PostMapping("/process_register")
    public String processRegister(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @RequestMapping(value = "/usernames",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllUsernames(){
        String[] arr = userService.listEmails();

        return ResponseEntity.status(HttpStatus.OK).body(arr);
    }
}
