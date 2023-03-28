package com.cos.blog.controllrer.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;

//데이터만 리턴 해줄거임
@RestController
public class UserApiController {
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		//실제로 DB에 insert를 하고 아래에서 return 하면 됨
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
}
