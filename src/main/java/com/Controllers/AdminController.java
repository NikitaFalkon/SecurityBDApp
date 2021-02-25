package com.Controllers;

import com.Model.User;
import com.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("")
    public String Users(Model model)
    {
        model.addAttribute("users", userService.AllUsers());
        return "users";
    }
    /*@GetMapping("/add")
    public String AddUser(Model model)
    {
        model.addAttribute("user", new User());
        return "add";
    }
    @PostMapping("/add")
    public String Adding(@RequestParam(name = "name", required = false) String name,
                         @RequestParam(name = "password", required = false) String password,
                         Model model)
    {
        User user = new User(name, password);
        userService.newUser(user);
        return "redirect: users";
    }*/

    @PostMapping("/dell")
    public String DellUser(@RequestParam(name = "id", required = false) int id)
    {
        userService.dellUser(id);
        return "redirect: hello";
    }

}
