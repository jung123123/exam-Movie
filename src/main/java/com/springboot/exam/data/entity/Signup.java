package com.springboot.exam.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "signup")  //테이블 이름
public class Signup {

    @Id//기본키
    private String MemberId;   //아이디

    @Column(nullable = false,columnDefinition = "VARCHAR(20)")   //null 값 불가능, 최대 길이 20
    private String MemberPwd;   //비밀번호

    @Column(columnDefinition = "VARCHAR(20)")   //null 값 가능, 최대 길이 20
    private String MemberEmail;   //이메일

}
