package vn.cdw.cdwforums.controller;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import vn.cdw.cdwforums.controller.form.UserRegistrationForm;
import vn.cdw.cdwforums.controller.validator.UserFormValidator;
import vn.cdw.cdwforums.entity.User;
import vn.cdw.cdwforums.service.UserService;

@Controller
public class AuthController {
    private UserService userService;
    private UserFormValidator userFormValidator;

    @Autowired
    public AuthController(UserService userService, UserFormValidator userFormValidator) {
        this.userService = userService;
        this.userFormValidator = userFormValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Registration");
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid UserRegistrationForm userRegistrationForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        User user = new User();
        user.setUsername(userRegistrationForm.getUsername());
        user.setEmail(userRegistrationForm.getEmail());
        user.setPassword(userRegistrationForm.getPassword());
        user.setDateOfRegistration(new Date());

        userService.signupUser(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error) {

        model.addAttribute("title", "Sign In");

        if (error != null)
            model.addAttribute("error", "Tài khoản hoặc mật khẩu không trùng.");

        return "auth/login";
    }
}
