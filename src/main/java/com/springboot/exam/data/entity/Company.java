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
@Table(name = "company")  //테이블 이름
public class Company {


    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성
    private Long CompanyNumber;   //번호

    @Column(nullable = false,columnDefinition = "VARCHAR(20)")   //null 값 불가능, 최대 길이 20
    private String CompanyName;   //이름


}
