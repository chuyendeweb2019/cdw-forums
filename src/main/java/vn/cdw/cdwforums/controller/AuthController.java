package vn.cdw.cdwforums.controller;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
   
    @GetMapping("/isUsername")
    public @ResponseBody boolean isUser(@RequestParam String username) {
        
        return  this.userService.loadByUsername(username);    }
    
    @GetMapping("/isEmail")
    public @ResponseBody boolean isEmail(@RequestParam String email) {
        
        return  this.userService.loadUserByEmail(email);    }
//   
//    @PostMapping("/registration")
//    public String registration(
//            @Valid UserRegistrationForm userRegistrationForm,
//            BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "auth/registration";
//        }
//
//        User user = new User();
//        user.setUsername(userRegistrationForm.getUsername());
//        user.setEmail(userRegistrationForm.getEmail());
//        user.setPassword(userRegistrationForm.getPassword());
//        user.setDateOfRegistration(new Date());
//
//        userService.signupUser(user);
//
//        return "redirect:/";
//    }
    @PostMapping("/ajax-registration")
    public @ResponseBody boolean ajaxRegistration(
            @RequestBody UserRegistrationForm userRegistrationForm) {
        User user = new User();
        user.setUsername(userRegistrationForm.getUsername());
        user.setEmail(userRegistrationForm.getEmail());
        user.setPassword(userRegistrationForm.getPassword());
        user.setDateOfRegistration(new Date());
        try {
      	 userService.signupUser(user);     
      	 return true;
		} catch (Exception e) {
			// TODO: handle exception
		  return false;
	}
      
    }

    @GetMapping("/login")
    public String login(Model model, String error) {

        model.addAttribute("title", "Sign In");

        if (error != null)
            model.addAttribute("error", "Tài khoản hoặc mật khẩu không trùng.");

        return "auth/login";
    }
}
