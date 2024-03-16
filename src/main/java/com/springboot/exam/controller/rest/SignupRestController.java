package com.springboot.exam.controller.rest;


import com.springboot.exam.data.dto.SignupDto.ResponseSignupDto;
import com.springboot.exam.data.dto.SignupDto.SignupDto;
import com.springboot.exam.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signup")
public class SignupRestController {


    @Autowired
    SignupService signupService;

    @PostMapping("/{id}/{pwd}/{email}")
    public ResponseEntity<ResponseSignupDto> createSignup(@PathVariable String id,@PathVariable String pwd,@PathVariable String email) {       //Signup 테이블에 값 추가 insert

        SignupDto signupDto = new SignupDto();
        signupDto.setMemberId(id);
        signupDto.setMemberPwd(pwd);
        signupDto.setMemberEmail(email);
        ResponseSignupDto responseSignupDto = signupService.saveSignup(signupDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseSignupDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
    }

    @GetMapping("/{id}/{pwd}")
    public ResponseEntity<SignupDto> loginSignup(@PathVariable String id,@PathVariable String pwd){                //Signup 테이블 리스트 보기 select
        SignupDto signupDto =  signupService.loginSignup(id,pwd);
        return ResponseEntity.status(HttpStatus.OK).body(signupDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }

    @GetMapping("/{id}")
    public ResponseEntity<SignupDto> loginSignup(@PathVariable String id){                //Signup 테이블 리스트 보기 select
        SignupDto signupDto =  signupService.checkSignup(id);               //id 중복 체크
        return ResponseEntity.status(HttpStatus.OK).body(signupDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }



    @GetMapping("/getAll")
    public ResponseEntity<List<SignupDto>> getAllSignup(){                //Signup 테이블 리스트 보기 select
        List<SignupDto> listsDto =  signupService.selectAllSignup();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }

    @PutMapping("/update/{id}/{pwd}/{email}")
    public ResponseEntity<ResponseSignupDto> updateSignup(            //Signup 테이블 수정 update
        @PathVariable String id,@PathVariable String pwd,@PathVariable String email) throws Exception {

        ResponseSignupDto responseSignupDto = signupService.updateSignup(id, pwd,email);

        return ResponseEntity.status(HttpStatus.OK).body(responseSignupDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSignup(@PathVariable String id) throws Exception {       //Signup 테이블 삭제 delete
        signupService.deleteSignup(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }



}
