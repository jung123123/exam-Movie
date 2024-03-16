package com.springboot.exam.data.dto.MovieDto;


import com.springboot.exam.data.entity.Company;
import com.springboot.exam.data.entity.Genre;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MovieResponseFileDto {

    private Long MovieNumber;   // 번호
    private String MovieName;   // 이름
    private Genre genre;        // 장르
    private Company company;    // 제작사
    private String MovieAddress;    // 주소
    private MultipartFile movieImageFile;   // 업로드된 파일 (포스터)
}
