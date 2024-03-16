package com.springboot.exam.data.dto.MovieDto;

import com.springboot.exam.data.entity.Company;
import com.springboot.exam.data.entity.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieNameDto {

    private Long MovieNumber;   //번호

    private String MovieName;   //이름

    private String genre;        //장르

    private String company;    //제작사

    private String MovieImg;    //포스터

    private String MovieAddress;    //주소
}
