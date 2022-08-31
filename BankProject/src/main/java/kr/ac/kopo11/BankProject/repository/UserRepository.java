package kr.ac.kopo11.BankProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.kopo11.BankProject.domain.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserid(String userId);

	void deleteByUserid(String userid);

}
