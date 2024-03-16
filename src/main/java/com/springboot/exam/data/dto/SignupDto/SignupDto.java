package com.springboot.exam.data.dto.SignupDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {

    private String MemberId;   //아이디

    private String MemberPwd;   //비밀번호

    private String MemberEmail;   //이메일
}
