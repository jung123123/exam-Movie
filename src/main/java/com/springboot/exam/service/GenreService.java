package com.springboot.exam.service;

import com.springboot.exam.data.dto.GenreDto.GenreDto;
import com.springboot.exam.data.dto.GenreDto.ResponseGenreDto;


import java.util.List;

public interface GenreService {

    List<GenreDto> selectAllGenre();  //Genre 테이블에 값 추가 select

    ResponseGenreDto saveGenre(GenreDto genreDto);  //Genre 테이블에 값 추가 insert

    String saveGenreName(String name);  //Genre 테이블에 GenreName값 추가 insert

    ResponseGenreDto updateGenre(Long id, String name) throws Exception;        //Genre 테이블 수정 update

    void deleteGenre(Long id) throws Exception;       //Genre 테이블 삭제 delete
}
