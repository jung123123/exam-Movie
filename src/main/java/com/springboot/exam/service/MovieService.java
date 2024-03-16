package com.springboot.exam.service;


import com.springboot.exam.data.dto.MovieDto.*;
import com.springboot.exam.data.entity.Company;
import com.springboot.exam.data.entity.Genre;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

public interface MovieService {

    List<MovieDto> selectAllMovie();  //Movie 테이블에 값 추가 select

    List<MovieNameDto> selectNameFindMovie(String name);  //Movie 테이블에 값 추가 select

    List<MovieNameDto> selectNameMovie();  //Movie 테이블에 값 추가 select

    ResponseMovieDto saveMovie(MovieDto movieDto);  //Movie 테이블에 값 추가 insert


    String saveImgMovie(MovieImgDto movieDto, MultipartFile file);  //Movie 테이블에 값 추가 insert

    String saveNotImgMovie(MovieImgDto movieDto); //Movie 테이블에 값 추가 insert


    MovieResponseFileDto saveFileMovie(MovieFileDto movieFileDto);  //Movie 테이블에 값 추가 insert

    ResponseMovieDto updateMovie(Long id, String name, Genre genre, Company company, String address, String Img) throws Exception;        //Movie 테이블 수정 update

    void deleteMovie(Long id) throws Exception;       //Movie 테이블 삭제 delete

    void deleteAll() throws Exception;      // Movie  Company Genre 테이블 삭제

}
