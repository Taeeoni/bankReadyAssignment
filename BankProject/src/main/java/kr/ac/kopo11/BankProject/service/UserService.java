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

	public User update(User user) {
		
		User userNow = findByUserid(user.getUserid()).get();
		
		if(userNow == null) {
			return null;
		}
		
		userNow.setUserid(user.getUserid());
		userNow.setPassword(user.getPassword());
		userNow.setName(user.getName());
		userNow.setBirth(user.getBirth());
		userNow.setAddress(user.getAddress());
		userNow.setPhone(user.getPhone());
		
		save(userNow);
		
		return userNow;
	}

	public User patch(User user) {
		
		User patchedUser = findByUserid(user.getUserid()).get();
		
		if(patchedUser == null) {
			return null;
		}
		
		if(user.getAddress() != null) {
			patchedUser.setAddress(user.getAddress());
		}
		
		if(user.getBirth() != null) {
			patchedUser.setBirth(user.getBirth());
		}
		
		if(user.getName() != null) {
			patchedUser.setName(user.getName());
		}
		
		if(user.getPassword() != null) {
			patchedUser.setPassword(user.getPassword());
		}
		
		if(user.getPhone() != null) {
			patchedUser.setPhone(user.getPhone());
		}
				
		save(patchedUser);
		
		return patchedUser;
	}


}
