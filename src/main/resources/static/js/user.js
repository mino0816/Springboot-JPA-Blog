let index = {
	init: function(){
		$("#btn-save").on("click", ()=>{ //function(){}을 사용하지 않는이유는 this를 바인딩 하기 위해서 사용함
			this.save();
		});
		$("#btn-update").on("click", ()=>{ //function(){}을 사용하지 않는이유는 this를 바인딩 하기 위해서 사용함
			this.update();
		});
	},

	save : function(){
		//alert('user의save함수 호출됨');
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		}; 
	//console.log(data);
		//ajax호출시 default가 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청 
		$.ajax({		
		type : "POST",
		url: "/auth/joinProc",
		data : JSON.stringify(data),	//http body데이터
		contentType : "application/json; charset=utf-8",		//body데이터가 어떤 타입인지
		dataType : "json"		//요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 오브젝트로 변경
		}).done(function(resp){
			alert("회원가입이 완료되었습니다");
			//console.log(resp); //ResponseDto가 리턴 됨
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	update : function(){
		//alert('user의save함수 호출됨');
		let data = {
			id :$("#id").val(),
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		}; 
		$.ajax({		
		type : "PUT",
		url: "/user",
		data : JSON.stringify(data),	
		contentType : "application/json; charset=utf-8",	
		dataType : "json"		
		}).done(function(resp){
			alert("회원수정이 완료되었습니다");
			//console.log(resp); //ResponseDto가 리턴 됨
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	
}

index.init();