package kr.ac.kopo11.BankProject.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo11.BankProject.domain.User;
import kr.ac.kopo11.BankProject.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@RequestMapping(value="/home")
//	public String LoginHome(Model model) {
//		
//		return "home";
//	}
	
	@PostMapping(value="/write") // CREATE, POST
	public User insertUser(Model model, @RequestBody User user) {
			
		userService.validateDuplicateUser(user);

		return userService.save(user);
	}
	
	
	@GetMapping(value="/allRead") // GET ALL 
	public ResponseEntity<List<User>> allRead(Model model) {
		
		List<User> users = userService.findAll();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/oneRead") // GET ONE
	public ResponseEntity<User> oneRead(Model model, @RequestParam(value = "id") String userid ) {
		
		User user = userService.findByUserid(userid).get();
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@PutMapping(value="/update") // UpdateAll, PUT
	public ResponseEntity<User> updateUser(Model model, @RequestBody User user) {
			

			User userUpdated = userService.update(user);

			return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
	}
	

	
	@PatchMapping(value="/updatePatch") // PATCH
	public ResponseEntity<User> updatePatch(Model model, @RequestBody User user) {
		
		User patchedUser = userService.patch(user);
		
	
		return new ResponseEntity<User>(patchedUser, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value="/delete") //DELETE
	@Transactional
	public String delete(Model model, @RequestParam(value = "id") String userid) {
		userService.delete(userid);
		return "????????? ???????????????.";
	}
	
	@ExceptionHandler({NoSuchElementException.class , NullPointerException.class})
	public Object nullEx(Exception e) {
		
		return "TaeeonServiceException";
	}
	
	
	
	
}
