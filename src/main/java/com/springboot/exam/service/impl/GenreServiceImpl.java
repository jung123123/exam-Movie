package com.springboot.exam.service.impl;

import com.springboot.exam.data.dto.GenreDto.GenreDto;
import com.springboot.exam.data.dto.GenreDto.ResponseGenreDto;
import com.springboot.exam.data.entity.Genre;
import com.springboot.exam.data.repository.GenreRepository;
import com.springboot.exam.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public  GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDto> selectAllGenre() {         //Genre 테이블에 값 추가 select
        List<Genre> list = genreRepository.findAll();

        List<GenreDto> listDto = new ArrayList<>();

        for (Genre g : list){
            GenreDto genreDto = new GenreDto();
            genreDto.setGenreNumber(g.getGenreNumber());
            genreDto.setGenreName(g.getGenreName());

            listDto.add(genreDto);

        }

        return listDto;
    }


    @Override
    public ResponseGenreDto saveGenre(GenreDto genreDto) {       //Genre 테이블에 값 추가 insert
        Genre genre = new Genre();

        genre.setGenreName(genreDto.getGenreName());

        Genre saveGenre = genreRepository.save(genre);

        ResponseGenreDto responseGenreDto = new ResponseGenreDto();
        responseGenreDto.setGenreNumber(saveGenre.getGenreNumber());
        responseGenreDto.setGenreName(saveGenre.getGenreName());


        return responseGenreDto;
    }

    @Override
    public String saveGenreName(String name) {      //Genre 추가
        Genre genre = new Genre();

        genre.setGenreName(name);

        genreRepository.save(genre);

        return "Success";
    }

    @Override
    public ResponseGenreDto updateGenre(Long id, String name) throws Exception {      //Genre 테이블 수정 update
        Optional<Genre> genreOptional = genreRepository.findById(id);

        Genre updateGenre;
        if (genreOptional.isPresent()) {
            Genre genre = genreOptional.get();

            genre.setGenreName(name);

            updateGenre = genreRepository.save(genre);
        } else {
            throw new Exception();
        }

        ResponseGenreDto responseGenreDto =  new ResponseGenreDto();
        responseGenreDto.setGenreNumber(updateGenre.getGenreNumber());
        responseGenreDto.setGenreName(updateGenre.getGenreName());

        return responseGenreDto;
    }

    @Override
    public void deleteGenre(Long id) throws Exception {         //Genre 테이블 삭제 delete
        Optional<Genre> genreOptional = genreRepository.findById(id);

        if (genreOptional.isPresent()) {
            Genre genre = genreOptional.get();

            genreRepository.delete(genre);

        } else {
            throw new Exception();
        }
    }
}
