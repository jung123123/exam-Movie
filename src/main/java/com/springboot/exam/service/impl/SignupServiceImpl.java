package com.springboot.exam.service.impl;

import com.springboot.exam.data.dto.SignupDto.ResponseSignupDto;
import com.springboot.exam.data.dto.SignupDto.SignupDto;
import com.springboot.exam.data.entity.Signup;
import com.springboot.exam.data.repository.SignupRepository;
import com.springboot.exam.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SignupServiceImpl implements SignupService {

    private final SignupRepository signupRepository;

    @Autowired
    public  SignupServiceImpl(SignupRepository signupRepository){
        this.signupRepository = signupRepository;
    }

    @Override
    public List<SignupDto> selectAllSignup() {      //Signup 테이블에 값 추가 select
        List<Signup> list = signupRepository.findAll();

        List<SignupDto> listDto = new ArrayList<>();

        for (Signup s : list){
            SignupDto signupDto = new SignupDto();
            signupDto.setMemberId(s.getMemberId());
            signupDto.setMemberPwd(s.getMemberPwd());
            signupDto.setMemberEmail(s.getMemberEmail());

            listDto.add(signupDto);

        }

        return listDto;
    }

    @Override
    public SignupDto loginSignup(String id, String pwd) {       //아이디 비밀 번호 체크

       Optional<Signup> optionalSignup = signupRepository.findById(id);

        if (optionalSignup.isPresent()) {
            Signup signup = optionalSignup.get();

            if (pwd.equals(signup.getMemberPwd())) {
                SignupDto signupDto = new SignupDto();
                signupDto.setMemberId(signup.getMemberId());
                signupDto.setMemberPwd(signup.getMemberPwd());

                return signupDto;
            }
        }

        return null;
    }

    @Override
    public SignupDto checkSignup(String id) {           // 아이디 중복 체크
        Optional<Signup> optionalSignup = signupRepository.findById(id);
        if (optionalSignup.isPresent()) {
            Signup signup = optionalSignup.get();

            SignupDto signupDto = new SignupDto();
            signupDto.setMemberId(signup.getMemberId());
            signupDto.setMemberPwd(signup.getMemberPwd());
            signupDto.setMemberEmail(signup.getMemberEmail());

            return signupDto;
        }
        return null;
    }

    @Override
    public String loginCheck(SignupDto signupDto) {     // 로그인 체크
        Optional<Signup> optionalSignup = signupRepository.findById(signupDto.getMemberId());
        if(optionalSignup != null){
            Signup uu = optionalSignup.get();
            if(signupDto.getMemberPwd().equals(uu.getMemberPwd())){
                return "YES";
            }
        }
        return "NO";
    }

    @Override
    public ResponseSignupDto saveSignup(SignupDto signupDto) {       //Signup 테이블에 값 추가 insert
        Signup signup = new Signup();

        signup.setMemberId(signupDto.getMemberId());
        signup.setMemberPwd(signupDto.getMemberPwd());
        signup.setMemberEmail(signupDto.getMemberEmail());

        Signup saveSignup = signupRepository.save(signup);

        ResponseSignupDto responseSignupDto = new ResponseSignupDto();
        responseSignupDto.setMemberId(saveSignup.getMemberId());
        responseSignupDto.setMemberPwd(saveSignup.getMemberPwd());
        responseSignupDto.setMemberEmail(saveSignup.getMemberEmail());

        return responseSignupDto;
    }

    @Override
    public ResponseSignupDto updateSignup(String id, String pwd, String email) throws Exception {   //Signup 테이블 수정 update

        Optional<Signup> signupOptional = signupRepository.findById(id);

        Signup updateSignup;
        if (signupOptional.isPresent()) {
            Signup signup = signupOptional.get();

            signup.setMemberPwd(pwd);
            signup.setMemberEmail(email);

            updateSignup = signupRepository.save(signup);
        } else {
            throw new Exception();
        }

        ResponseSignupDto responseSignupDto =  new ResponseSignupDto();
        responseSignupDto.setMemberId(updateSignup.getMemberId());
        responseSignupDto.setMemberPwd(updateSignup.getMemberPwd());
        responseSignupDto.setMemberEmail(updateSignup.getMemberEmail());



        return responseSignupDto;
    }

    @Override
    public void deleteSignup(String id) throws Exception {      //Signup 테이블 삭제 delete
        Optional<Signup> signupOptional = signupRepository.findById(id);

        if (signupOptional.isPresent()) {
            Signup signup = signupOptional.get();

            signupRepository.delete(signup);

        } else {
            throw new Exception();
        }
    }
}
