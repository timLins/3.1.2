package kata.project.controller;


import kata.project.model.User;
import kata.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    //вывод всех пользователей по начальному запросу /users
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users/index";
    }
    //показать пользователя по id
    @GetMapping("/show")
    public String show(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/users/show";
    }
    //страница создания нового пользователя и передача post запроса на /users
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }
    //получение от get запроса /new и сохранение пользователя
    @PostMapping()
    public String create(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userService.add(user);
        return "redirect:/users";
    }

    //запрос на изменения пользователя по id и patch запрос /{id}
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable(value = "id")int id) {
        model.addAttribute("user", userService.getUser(id));
        return "/users/edit";
    }
    //получение пользователя и обновление
    @PostMapping("/update")
    public String update(@RequestParam(value = "id")int id,@ModelAttribute(value = "user")@Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.update(id,user);
        return "redirect:/users";
    }
    //удаление пользователя
    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id")int id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
