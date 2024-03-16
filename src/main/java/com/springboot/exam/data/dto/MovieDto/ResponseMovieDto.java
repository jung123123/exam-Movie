package com.springboot.exam.data.dto.MovieDto;


import com.springboot.exam.data.entity.Company;
import com.springboot.exam.data.entity.Genre;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@Getter
@Setter
public class ResponseMovieDto {

    private Long MovieNumber;   //번호

    private String MovieName;   //이름

    private Genre genre;        //장르

    private Company company;    //제작사

    private String MovieAddress;    //주소

    private String MovieImg;    //포스터

}
