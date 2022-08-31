package kr.ac.kopo11.BankProject.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo11.BankProject.domain.User;
import kr.ac.kopo11.BankProject.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findByUserid(String userId) {
		return userRepository.findByUserid(userId);
	}

	public void delete(String userid) {
		userRepository.deleteByUserid(userid);
	}


}