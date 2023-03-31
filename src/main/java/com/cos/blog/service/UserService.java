package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. Ioc를 해준다
//서비스가 필요한 이유 
//1.트랜잭션관리 2.서비스의미(ex.송금서비스)
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//회원가입 메서드가 성공하면 commit이 되고 실패 하면 롤백이 된다
	@Transactional
	public void 회원가입(User user) {		
		String rowPassword = user.getPassword(); //원본 pw
		String encPassword = encoder.encode(rowPassword); //해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);		
	}
	
	/*
	 * @Transactional(readOnly = true) //Select 할 때 트렌잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성
	 * 유지) public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword());
	 * }
	 */
}
