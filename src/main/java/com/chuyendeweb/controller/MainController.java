package com.chuyendeweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chuyendeweb.entity.Post;
import com.chuyendeweb.services.IServicesPost;


@Controller
public class MainController {

	@Autowired
	IServicesPost servicesPost;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String MainApp(Model model) {
		List<Post> list = servicesPost.getAllPost();
		model.addAttribute("list",list);
		return "/forum/home";
	}
	@RequestMapping(value = "error/404", method = RequestMethod.GET)
	public String error404() {
		return "/error/404";
	}
	
	@GetMapping("/login")	
	public String login() {
		return "/forum/user-login";
	}
}
