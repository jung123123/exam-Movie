package com.springboot.exam.controller;

import com.springboot.exam.data.dto.CompanyDto.CompanyDto;
import com.springboot.exam.data.dto.GenreDto.GenreDto;
import com.springboot.exam.data.dto.MovieDto.MovieDto;
import com.springboot.exam.data.dto.MovieDto.MovieNameDto;
import com.springboot.exam.data.entity.Genre;
import com.springboot.exam.service.CompanyService;
import com.springboot.exam.service.GenreService;
import com.springboot.exam.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/lists")
public class MovieListController {

    @Autowired
    GenreService genreService;

    @Autowired
    CompanyService companyService;

    @Autowired
    MovieService movieService;


    @GetMapping("")
    public String getAllList(Model model){      // genre company movie 테이블 select
        List<GenreDto> genre =  genreService.selectAllGenre();
        model.addAttribute("genres", genre);

        List<CompanyDto> company =  companyService.selectAllCompany();
        model.addAttribute("companys", company);

        List<MovieNameDto> movie =  movieService.selectNameMovie();
        model.addAttribute("movies", movie);
        return "movieList";
    }

    @GetMapping("/deletegenre")     // genre delete
    public String deleteGenre(Long genreNumber) throws Exception {
        genreService.deleteGenre(genreNumber);
        return "redirect:/lists";
    }

    @GetMapping("/deletecompany")    // company delete
    public String deleteCompany(Long companyNumber) throws Exception {
        companyService.deleteCompany(companyNumber);
        return "redirect:/lists";
    }

    @GetMapping("/deletemovie")  // movie delete
    public String deleteMovie(Long movieNumber) throws Exception {
        movieService.deleteMovie(movieNumber);
        return "redirect:/lists";
    }

    @GetMapping("/delete")      // 기본 값
    public String deleteAll() throws Exception {
        movieService.deleteAll();

        String[] genre = {"액션","범죄","SF","판타지","슬랩스틱","로맨스","호러","블랙 코미디","스릴러"};

        String[] company = {"넷플릭스","유니버설 스튜디오","메가박스","Lionsgate Films","빅펀치픽쳐스","닌텐도","디즈니","스카이댄스 미디어","마블 스튜디오"};


        for(int i = 0; i < genre.length; i ++){
            genreService.saveGenreName(genre[i]);
        }

        for(int i = 0; i < company.length; i ++){
            companyService.saveCompanyName(company[i]);
        }


        return "redirect:/list";


    }

}
