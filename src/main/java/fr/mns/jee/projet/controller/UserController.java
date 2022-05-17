package fr.mns.jee.projet.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import fr.mns.jee.projet.errors.InvalidParameterException;
import fr.mns.jee.projet.errors.NotFoundException;
import fr.mns.jee.projet.dto.EditRequestDTO;
import fr.mns.jee.projet.service.dto.UserConverter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import fr.mns.jee.projet.model.*;
import fr.mns.jee.projet.service.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserConverter userConverter;


	@GetMapping(value="/page")
	public ResponseEntity<Page<User>> getPage(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize) {
		if(pageNumber == null || pageSize == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(userService.findAll(pageNumber, pageSize));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable("id") Long id) throws InvalidParameterException, NotFoundException {
		if (id == null || id < 0) {
			throw new InvalidParameterException("id");
		}

		Optional<User> user = userService.getById(id);

		if (!user.isPresent()) {
			throw new NotFoundException(id);
		}

		return ResponseEntity.ok(user.get());
	}

	@ApiOperation(value = "create", notes = "create new User", nickname = "createUser")
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody @Valid EditRequestDTO request) throws URISyntaxException {
		User user = userService.save(userConverter.convert(request));

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody @Valid EditRequestDTO request) throws URISyntaxException{
		User user = userService.findById(id);
		userService.save(userConverter.convert(user, request));
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		try {
			return userService.findByUsername(username);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	@GetMapping("/")
	public ModelAndView getUsers() {
		ModelAndView mv = new ModelAndView("users");
		mv.addObject("users", userService.findByGender(Gender.FEMALE));
		return mv;
	}

	@GetMapping("/addUsers")
	public ModelAndView createUsersForm() {
		ModelAndView mv = new ModelAndView("userForm");
		mv.addObject("user", new User());
		return mv;
	}

	@PostMapping("/addUser")
	public String saveUser(@ModelAttribute("userForm") User user) {
		userService.save(user);
		return "redirect:/users";
	}

	 */

}