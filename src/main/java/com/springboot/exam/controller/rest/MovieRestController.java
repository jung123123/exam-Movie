package com.springboot.exam.controller.rest;


import com.springboot.exam.data.dto.GenreDto.GenreDto;
import com.springboot.exam.data.dto.GenreDto.ResponseGenreDto;
import com.springboot.exam.data.dto.MovieDto.*;
import com.springboot.exam.data.entity.Company;
import com.springboot.exam.data.entity.Genre;
import com.springboot.exam.service.MovieService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@MultipartConfig(maxFileSize = 1024*1024*2, maxRequestSize = 1024 * 1024 * 10)
@RequestMapping("/movie")
public class MovieRestController {

    @Autowired
    MovieService movieService;

//    @PostMapping("/name={name}/genre={genre}/company={company}/img={file}")
//    public String createFileMovie(
//            @PathVariable String name,
//            @PathVariable Genre genre,
//            @PathVariable Company company,
//            @RequestParam("file") MultipartFile file
//    ){
//
//
//        MovieDto movieDto = new MovieDto();
//        movieDto.setMovieName(name);
//        movieDto.setGenre(genre);
//        movieDto.setCompany(company);
//        movieService.saveImgMovie(movieDto,file);
//
//        return "SUCCESS";
//    }
//
//    @PostMapping()
//    public String createFileMovie(@RequestBody MovieDto movieDto,@RequestParam("img") MultipartFile img) {
//        movieService.saveImgMovie(movieDto,img);
//        return "Success";  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
//    }



    @PostMapping("/name={name}/genre={genre}/company={company}")                // Movie 테이블 추가   insert
    public ResponseEntity<ResponseMovieDto> createMovie(
            @PathVariable String name,
            @PathVariable Genre genre,
            @PathVariable Company company
    ){


        MovieDto movieDto = new MovieDto();
        movieDto.setMovieName(name);
        movieDto.setGenre(genre);
        movieDto.setCompany(company);


        ResponseMovieDto responseMovieDto = movieService.saveMovie(movieDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMovieDto);
    }


//    @PostMapping()
//    public ResponseEntity<ResponseMovieDto> createMovie(@RequestBody MovieDto movieDto) {
//        ResponseMovieDto responseMovieDto = movieService.saveMovie(movieDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseMovieDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
//    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDto>> getAllMovie(){                // Movie 테이블 리스트 보기 select
        List<MovieDto> listsDto =  movieService.selectAllMovie();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }



    @GetMapping("/{name}")
    public ResponseEntity<List<MovieNameDto>> getNameFindMovie(@PathVariable String name){                // Movie 테이블 리스트 보기 select
        List<MovieNameDto> listsDto =  movieService.selectNameFindMovie(name);
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }

    @GetMapping("/getName")
    public ResponseEntity<List<MovieNameDto>> getNameMovie(){                // Movie 테이블 리스트 보기 select
        List<MovieNameDto> listsDto =  movieService.selectNameMovie();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }



    @PutMapping("/id={id}/name={name}/genre={genre}/company={company}/address={address}/img={img}")         // Movie 테이블 수정 update
    public ResponseEntity<ResponseMovieDto> updateMovie(@PathVariable Long id, @PathVariable String name,
       @PathVariable Genre genre, @PathVariable Company company, @PathVariable String address, @PathVariable String img) throws Exception {


        ResponseMovieDto responseMovieDto = movieService.updateMovie(id,name,genre,company,address,img);

        return ResponseEntity.status(HttpStatus.OK).body(responseMovieDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) throws Exception {       // Movie 테이블 삭제 delete
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() throws Exception {       // Movie 테이블 삭제 delete
        movieService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
