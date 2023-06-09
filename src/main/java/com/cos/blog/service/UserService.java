package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {		
		
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	
	//회원가입 메서드가 성공하면 commit이 되고 실패 하면 롤백이 된다
	@Transactional
	public void 회원가입(User user) {		
		String rowPassword = user.getPassword(); //원본 pw
		String encPassword = encoder.encode(rowPassword); //해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);		
	}
	
	@Transactional
	public void 회원수정 (User user) {
		//수정시에는 영속성컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User오브젝트를 수정
		//select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서
		//영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주거든요.
		User persistance =userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
		//Validate체크 kakao라고 적혀있는 사람은 여기를 못탐
		if(persistance.getOauth()==null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		
	
		
		
	}
	
	/*
	 * @Transactional(readOnly = true) //Select 할 때 트렌잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성
	 * 유지) public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword());
	 * }
	 */
}
