package com.springboot.exam.service;

import com.springboot.exam.data.dto.SignupDto.ResponseSignupDto;
import com.springboot.exam.data.dto.SignupDto.SignupDto;

import java.util.List;

public interface SignupService {


   List<SignupDto> selectAllSignup();  //Signup 테이블에 값 추가 select

   SignupDto loginSignup(String id, String pwd);   //Signup 테이블에 값 추가 select

   SignupDto checkSignup(String id);   //Signup 테이블에 값 추가 select  //아이디 중복 체크

   String loginCheck(SignupDto signupDto);//로그인 체크



   ResponseSignupDto saveSignup(SignupDto signupDto);  //Signup 테이블에 값 추가 insert

   ResponseSignupDto updateSignup(String id, String pwd,String email) throws Exception;        //Signup 테이블 수정 update

   void deleteSignup(String id) throws Exception;       //Signup 테이블 삭제 delete
}
