package vn.cdw.cdwforums.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

@RequestMapping("/ws")
public class MainController {
	@RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");
 
        if (username == null || username.isEmpty()) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
 
        return "fragments/footer";
    }
 
    @RequestMapping(path = "/login-socket", method = RequestMethod.GET)
    public String showLoginPage() {
        return "auth/login";
    }
 
    @RequestMapping(path = "/login-socket", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
        username = username.trim();
 
        if (username.isEmpty()) {
            return "auth/login";
        }
        request.getSession().setAttribute("username", username);
 
        return "redirect:/";
    }
 
    @RequestMapping(path = "/logout-socket")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();
         
        return "redirect:/\"auth/login\"";
    }

}
