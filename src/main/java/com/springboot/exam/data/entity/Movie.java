package com.springboot.exam.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")  //테이블 이름
public class Movie {

    @Id//기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성
    private Long MovieNumber;   //번호

    @Column(nullable = false,columnDefinition = "VARCHAR(20)")   //null 값 불가능, 최대 길이 20
    private String MovieName;   //이름

    @ManyToOne      //단방향
    @JoinColumn(name = "GenreNumber") //fk
    private Genre genre;        //장르

    @ManyToOne      //단방향
    @JoinColumn(name = "CompanyNumber") //fk
    private Company company;    //제작사

    @Column(columnDefinition = "VARCHAR(200)")   // 최대 길이 50
    private String MovieImg;    //포스터

    @Column(columnDefinition = "VARCHAR(500)")   //null 값 불가능, 최대 길이 150
    private String MovieAddress;    //주소

    @Transient  // 데이터베이스에 매핑하지 않는 필드임을 명시
    private MultipartFile movieImageFile;

}
