package main.wba_projekt.board.controller;

import main.wba_projekt.security.model.User;
import main.wba_projekt.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    private UserRepository userRepo;

    @Autowired
    public BoardController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    /**
     * MVC delievers index page
     *
     * @return index
     */
    @GetMapping("")
    public String viewHomagePage() {
        return "index";
    }

    /**
     * Sets up a view to register a new User
     *
     * @param model
     * @return signup-form with model added
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    /**
     * Delivers view of the ticketboard
     *
     * @return board
     */
    @RequestMapping("/board")
    public String showBoard() {

        return "board";
    }


}
