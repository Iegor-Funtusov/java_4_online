package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "users/user_all";
    }

    @GetMapping("/new")
    public String redirectToNewUser(Model model) {
        model.addAttribute("user", new User());
        return "users/user_new";
    }

    @PostMapping("/new")
    public String newUser(@ModelAttribute User user) {
        userDao.create(user);
        return "redirect:/users";
    }
}
