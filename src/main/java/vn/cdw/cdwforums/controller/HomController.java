package vn.cdw.cdwforums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomController {

	@GetMapping("/")
	public String getHome() {
		return "forum/home";
	}
	@GetMapping("/profile")
	public String getProfile() {
		return "forum/user-profile";
	}
	
	@GetMapping("/loggin")
	public String getLogin() {
		return "forum/user-login";
	}
	
	@GetMapping("/resetpassword")
	public String getResetPass() {
		return "forum/user-resetpass";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "forum/user-register";
	}
	
	@GetMapping("/educ-timeline")
	public String getEductime() {
		return "forum/educ-timeline";
	}
	
	@GetMapping("/educ-timeline-detail")
	public String getEductimeDetail() {
		return "forum/educ-timeline-detail";
	}
}
