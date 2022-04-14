package fr.mns.jee.projet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.mns.jee.projet.model.*;
import fr.mns.jee.projet.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ModelAndView getUsers() {
		ModelAndView mv = new ModelAndView("users");
		mv.addObject("users", userService.findByGender(Gender.FEMALE));
		return mv;
	}
/*
	@GetMapping("/{id}")
	public ModelAndView getUsers(@PathVariable("id") Long id) {
		if (id != null) {

			Optional<User> user = userService.getById(id);
			if (user.isPresent()) {
				ModelAndView mv = new ModelAndView("user");
				mv.addObject("user", user.get());
				return mv;
			}

		}
		return getUsers();

	}
	*/
	@GetMapping("/addUsers")
	public ModelAndView createUsersForm() {
		ModelAndView mv=new ModelAndView("userForm");
		mv.addObject("user", new User());
		return mv;
		
	}
	
	@PostMapping("/addUser")
	public String saveUser(@ModelAttribute("userForm") User user) {
		userService.save(user);
		
		return "redirect:/users";
		
	}

}