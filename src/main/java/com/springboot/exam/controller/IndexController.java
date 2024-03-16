package com.springboot.exam.controller;

import com.springboot.exam.data.dto.CompanyDto.CompanyDto;
import com.springboot.exam.data.dto.GenreDto.GenreDto;
import com.springboot.exam.data.dto.MovieDto.MovieDto;
import com.springboot.exam.data.dto.MovieDto.MovieNameDto;
import com.springboot.exam.data.entity.Movie;
import com.springboot.exam.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("")
public class IndexController {


    @Autowired
    MovieService movieService;


    @GetMapping("")
    public String index(Model model){   // movie 테이블 select
        List<MovieNameDto> movieList = movieService.selectNameMovie();
        model.addAttribute("movies",movieList);
        return "index";
    }

    @GetMapping("name")// 영화 이름 검색
    public String indexName(@RequestParam String name, Model model){
        List<MovieNameDto> listsDto =  movieService.selectNameFindMovie(name);
        model.addAttribute("movies",listsDto);
        return "index";
    }

    @GetMapping("/list")
    public String movieList(){return "list";}

    @GetMapping("/index")
    public String redirectToIndex() {
        return "redirect:/";
    }



}
