package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//User테이블을 관리하는 레파지토리고 user테이블의 pk는 int 타입이다
//DAO //자동으로 bean등록이 된다.
//@Repository // 생략 가능하다
public interface UserRepository extends JpaRepository<User, Integer>{
	//select * from user where username = ?;
	Optional<User> findByUsername(String username);
	
	//JPA Naming 쿼리 전략
	//select *from usre WHERE  username = ? AND password= ?; 이렇게 동작
	//User findByUsernameAndPassword(String username, String password);
}
