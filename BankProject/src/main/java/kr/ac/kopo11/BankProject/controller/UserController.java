package kr.ac.kopo11.BankProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
			
		
			userService.findByUserid(user.getUserid()).ifPresent(m -> {
	            throw new IllegalStateException("이미 존재하는 회원입니다.");
	        });


		return userService.save(user);
	}
	
	
	@GetMapping(value="/allRead") // GET ALL 
	@ResponseBody
	public List<User> allRead(Model model) {
		
		List<User> users = userService.findAll();
		
		return users;
	}
	
	
	@GetMapping(value="/oneRead") // GET ONE
	@ResponseBody
	public User oneRead(Model model, @RequestParam(value = "id") String userid ) {
		
		User user = userService.findByUserid(userid).get();
		
		return user;
	}
	
	
	@PutMapping(value="/update") // UpdateAll, PUT
	public User updateUser(Model model, @RequestBody User user) {

			User userNow = userService.findByUserid(user.getUserid()).get();
			userNow.setUserid(user.getUserid());
			userNow.setPassword(user.getPassword());
			userNow.setName(user.getName());
			userNow.setBirth(user.getBirth());
			userNow.setAddress(user.getAddress());
			userNow.setPhone(user.getPhone());

		return userService.save(userNow);
	}
	

	
	@PatchMapping(value="/updatePwd") // PATCH
	public User updatePwd(Model model, @RequestParam(value = "id") String userid, @RequestParam(value = "pwd") String pwd) {
		
		User user = userService.findByUserid(userid).get();
		user.setPassword(pwd);
		
		return userService.save(user);
	}
	
	
	@DeleteMapping(value="/delete") //DELETE
	public void delete(Model model, @RequestParam(value = "id") String userid) {
		userService.delete(userid);
	}
	
	
	
	
}
