package com.Controllers;

import com.Model.User;
import com.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("userForm", new User());
        return "registration";
    }
    @PostMapping("/registration")
    //public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model)
    public String addUser(@RequestParam(name = "username", required = false) String username,
                          @RequestParam(name = "password", required = false) String password, Model model)
    {
        /*if (bindingResult.hasErrors()) {
            return "registration";
        }*/
        /*if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Password is incorrect");
            return "registration";
        }*/
        System.out.println(password);
        User userForm = new User(username, password);
        if (!userService.newUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }
    @GetMapping("/login")
    public String Log(Model model)
    {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        model.addAttribute("userForm", new User());
        return "login";
    }
    @PostMapping("/login")
    public String Login(@ModelAttribute("userForm") @Valid User userForm, Model model)
/*    public String Login(@RequestParam(name = "username", required = false) String username,
                          @RequestParam(name = "password", required = false) String password, Model model)*/
    {
        System.out.println("EI");
        System.out.println(userForm.getPassword());
        System.out.println(userForm.getUsername());
        if(userService.findUser(userForm))
        {
            User user = userForm;
        }
        if (!userService.findUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем не существует");
            return "login";
        }
        return "redirect: menu";
    }
}
