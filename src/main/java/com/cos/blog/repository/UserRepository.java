package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//User테이블을 관리하는 레파지토리고 user테이블의 pk는 int 타입이다
//DAO //자동으로 bean등록이 된다.
//@Repository // 생략 가능하다
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
