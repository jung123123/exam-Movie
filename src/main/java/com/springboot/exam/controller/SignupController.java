package com.springboot.exam.controller;

import com.springboot.exam.data.dto.SignupDto.ResponseSignupDto;
import com.springboot.exam.data.dto.SignupDto.SignupDto;
import com.springboot.exam.data.entity.Signup;
import com.springboot.exam.service.SignupService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/s")
public class SignupController {

    @Autowired
    SignupService signupService;



    @GetMapping("/joinForm")
    public String joinForm(){   //회원가입 폼을 보여줌
        return "join";
    }

    @PostMapping("/join")       //회원 가입  Signup 테비을 insert
    public String join( SignupDto signup) {

        ResponseSignupDto j = signupService.saveSignup(signup);


        return "redirect:/";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "login";

    }

    @PostMapping("/loginCheck") // 로그인 체크
    public String loginCheck(SignupDto signupDto, HttpServletRequest request){
        //회원 check
        String result = signupService.loginCheck(signupDto);
        HttpSession session = request.getSession();

        if(result.equals("YES")){   //회원 맞음
            session.setAttribute("loginTag", "YES"); //로그인 성공 표시
        }
        else{   //회원 아님
            session.setAttribute("loginTag", "NO"); //로그인 성공 표시
        }

        return "redirect:/";

    }

    @GetMapping("/logout")      //로그아웃
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("loginTag", "NO");

        return "redirect:/";
    }



}
