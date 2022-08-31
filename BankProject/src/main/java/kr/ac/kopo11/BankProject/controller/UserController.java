package kr.ac.kopo11.BankProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo11.BankProject.domain.User;
import kr.ac.kopo11.BankProject.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/home")
	public String LoginHome(Model model) {
		
		return "home";
	}
	
	@RequestMapping(value="/write") // CREATE, POST
	public User insertUser(Model model, @RequestBody User user) {
			
		
			userService.findByUserid(user.getUserid()).ifPresent(m -> {
	            throw new IllegalStateException("이미 존재하는 회원입니다.");
	        });


		return userService.save(user);
	}
	
	
	@RequestMapping(value="/allRead") // GET ALL 
	public ResponseEntity<List<User>> allRead(Model model) {
		
		List<User> users = userService.findAll();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/oneRead") // GET ONE
	public ResponseEntity<User> oneRead(Model model, @RequestParam(value = "id") String userid ) {
		
		User user = userService.findByUserid(userid).get();
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/update") // UpdateAll, PUT
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
	

	
	@RequestMapping(value="/updatePwd") // PATCH
	public User updatePwd(Model model, @RequestParam(value = "id") String userid, @RequestParam(value = "pwd") String pwd) {
		
		User user = userService.findByUserid(userid).get();
		user.setPassword(pwd);
		
		return userService.save(user);
	}
	
	
	@RequestMapping(value="/delete") //DELETE
	public void delete(Model model, @RequestParam(value = "id") String userid) {
		userService.delete(userid);
	}
	
	
	
	
}
