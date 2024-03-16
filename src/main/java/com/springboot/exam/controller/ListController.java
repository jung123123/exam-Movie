package com.springboot.exam.controller;

import com.springboot.exam.data.dto.CompanyDto.CompanyDto;
import com.springboot.exam.data.dto.CompanyDto.ResponseCompanyDto;
import com.springboot.exam.data.dto.GenreDto.GenreDto;
import com.springboot.exam.data.dto.GenreDto.ResponseGenreDto;
import com.springboot.exam.data.dto.MovieDto.MovieDto;
import com.springboot.exam.data.dto.MovieDto.MovieImgDto;
import com.springboot.exam.data.dto.MovieDto.MovieNameDto;
import com.springboot.exam.data.dto.MovieDto.ResponseMovieDto;
import com.springboot.exam.service.CompanyService;
import com.springboot.exam.service.GenreService;
import com.springboot.exam.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/g")
public class ListController {

    @Autowired
    GenreService genreService;

    @Autowired
    CompanyService companyService;

    @Autowired
    MovieService movieService;


    @GetMapping("list")
    public String getAllList(Model model){
        return "list";
    }


    @PostMapping("/createGenre")        //Genr 테이블 insert
    public String GenreCreate( GenreDto genreDto) {

        ResponseGenreDto g = genreService.saveGenre(genreDto);
        return "redirect:/lists";
    }

    @PostMapping("/createCompany")      //company 테이블 insert
    public String CompanyCreate( CompanyDto companyDto) {

        ResponseCompanyDto c = companyService.saveCompany(companyDto);


        return "redirect:/lists";
    }


//    @PostMapping("/createMovie")
//    public String MovieCreate( MovieDto movieDto) {
//
//        ResponseMovieDto m = movieService.saveMovie(movieDto);
//
//
//        return "list";
//    }

    @PostMapping("/createMovie")            //movie 테이블 insert
    public String MovieCreate(MovieImgDto movieDto, @RequestParam(value = "file", required = false)MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            // 파일이 존재할 경우에만 처리
            movieService.saveImgMovie(movieDto, file);
        }else{
            movieService.saveNotImgMovie(movieDto);
        }


        return "redirect:/lists";
    }




}
