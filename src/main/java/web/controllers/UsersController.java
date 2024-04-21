package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.services.UsersService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService service;

    @Autowired
    public UsersController(UsersService service){
        this.service = service;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", service.allUsers());
        return "users/all_users";
    }

    @GetMapping("/{id}")
    public String userById(@RequestParam("user_id") int id, Model model) {
        model.addAttribute("userById", service.userById(id));
        return "users/user_by_id";
    }

    @GetMapping("/new_user")
    public String newUser(@ModelAttribute("new_user") User user) {
        return "users/new_user";
    }

    @PostMapping
    public String create(@ModelAttribute("new_user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/users/new_user";
        }
        service.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("user_id") int id,Model model){
        model.addAttribute("user", service.userById(id));
        return "users/edit_user";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult ,
                             @RequestParam("user_id") int id){
        if (bindingResult.hasErrors()){
            return "/users/edit_user";
        }
        service.updateUser(id,user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@RequestParam("user_id") int id){
        service.deleteUser(id);
        return "redirect:/users";
    }
}
