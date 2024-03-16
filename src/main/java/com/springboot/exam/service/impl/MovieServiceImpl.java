package com.springboot.exam.service.impl;


import com.springboot.exam.data.dto.MovieDto.*;
import com.springboot.exam.data.entity.Company;
import com.springboot.exam.data.entity.Genre;
import com.springboot.exam.data.entity.Movie;
import com.springboot.exam.data.repository.CompanyRepository;
import com.springboot.exam.data.repository.GenreRepository;
import com.springboot.exam.data.repository.MovieRepository;
import com.springboot.exam.service.MovieService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    private GenreRepository genreRepository;

    private CompanyRepository companyRepository;

    private EntityManager entityManager;

    @Value("${uploadPath}")
    private String UPLOAD_LOCATION;     // C:/Images

    @Autowired
    public  MovieServiceImpl(MovieRepository movieRepository,GenreRepository genreRepository,
                             CompanyRepository companyRepository,EntityManager entityManager){
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.companyRepository = companyRepository;
        this.entityManager = entityManager;
    }


    @Override
    public List<MovieDto> selectAllMovie() {     //Movie 테이블에 값 추가 select
        List<Movie> list = movieRepository.findAll();

        List<MovieDto> listDto = new ArrayList<>();

        for (Movie m : list){
            MovieDto movieDto = new MovieDto();
            movieDto.setMovieNumber(m.getMovieNumber());
            movieDto.setMovieName(m.getMovieName());
            movieDto.setGenre(m.getGenre());
            movieDto.setCompany(m.getCompany());
            movieDto.setMovieImg(m.getMovieImg());

            listDto.add(movieDto);

        }

        return listDto;
    }

    @Override
    public List<MovieNameDto> selectNameFindMovie(String name) {        // 영화 이름 찾기
        List<Movie> list = movieRepository.findMoviesByNameContaining(name);

        List<MovieNameDto> listDto = new ArrayList<>();

        for (Movie m : list) {
            MovieNameDto movieNameDto = new MovieNameDto();
            movieNameDto.setMovieNumber(m.getMovieNumber());
            movieNameDto.setMovieName(m.getMovieName());
            movieNameDto.setGenre(m.getGenre().getGenreName());
            movieNameDto.setCompany(m.getCompany().getCompanyName());
            movieNameDto.setMovieImg(m.getMovieImg());

            listDto.add(movieNameDto);
        }

        return listDto;
    }

    @Override
    public List<MovieNameDto> selectNameMovie() {       // GenreName,CompanyNam 으로 select(출력)
        List<Movie> list = movieRepository.findAll();

        List<MovieNameDto> listDto = new ArrayList<>();

        for (Movie m : list){
            MovieNameDto movieNameDto = new MovieNameDto();
            movieNameDto.setMovieNumber(m.getMovieNumber());
            movieNameDto.setMovieName(m.getMovieName());
            movieNameDto.setGenre(m.getGenre().getGenreName());
            movieNameDto.setCompany(m.getCompany().getCompanyName());
            movieNameDto.setMovieImg(m.getMovieImg());

            listDto.add(movieNameDto);

        }
        return listDto;
    }


    @Override
    public ResponseMovieDto saveMovie(MovieDto movieDto) { //Movie 테이블에 값 추가 insert
        Movie movie = new Movie();

        movie.setMovieName(movieDto.getMovieName());
        movie.setGenre(movieDto.getGenre());
        movie.setCompany(movieDto.getCompany());


        Movie saveMovie = movieRepository.save(movie);

        ResponseMovieDto responseMovieDto = new ResponseMovieDto();
        responseMovieDto.setMovieNumber(saveMovie.getMovieNumber());
        responseMovieDto.setMovieName(saveMovie.getMovieName());
        responseMovieDto.setGenre(saveMovie.getGenre());
        responseMovieDto.setCompany(saveMovie.getCompany());



        return responseMovieDto;
    }


    @Override
    public MovieResponseFileDto saveFileMovie(MovieFileDto movieFileDto) {  //파일 저장 ===> 포기
        Movie movie = new Movie();

        movie.setMovieName(movieFileDto.getMovieName());
        movie.setGenre(movieFileDto.getGenre());
        movie.setCompany(movieFileDto.getCompany());
        movie.setMovieImageFile(movieFileDto.getMovieImageFile());
        movie.setMovieAddress(movieFileDto.getMovieAddress());

        Movie saveMovie = movieRepository.save(movie);

        MovieResponseFileDto responseMovieDto = new MovieResponseFileDto();
        responseMovieDto.setMovieNumber(saveMovie.getMovieNumber());
        responseMovieDto.setMovieName(saveMovie.getMovieName());
        responseMovieDto.setGenre(saveMovie.getGenre());
        responseMovieDto.setCompany(saveMovie.getCompany());
        responseMovieDto.setMovieImageFile(saveMovie.getMovieImageFile());
        responseMovieDto.setMovieAddress(saveMovie.getMovieAddress());

        return responseMovieDto;
    }

    @Override
    public ResponseMovieDto updateMovie(Long id, String name, Genre genre, Company company, String address , String Img) throws Exception {  //Movie 테이블 수정 update
        Optional<Movie> movieOptional = movieRepository.findById(id);

        Movie updateMovie;
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();

            movie.setMovieName(name);
            movie.setGenre(genre);
            movie.setCompany(company);
            movie.setMovieImg(Img);
            movie.setMovieAddress(address);

            updateMovie = movieRepository.save(movie);
        } else {
            throw new Exception();
        }

        ResponseMovieDto responseMovieDto =  new ResponseMovieDto();
        responseMovieDto.setMovieNumber(updateMovie.getMovieNumber());
        responseMovieDto.setMovieName(updateMovie.getMovieName());
        responseMovieDto.setGenre(updateMovie.getGenre());
        responseMovieDto.setCompany(updateMovie.getCompany());
        responseMovieDto.setMovieImg(updateMovie.getMovieImg());
        responseMovieDto.setMovieAddress(updateMovie.getMovieAddress());

        return responseMovieDto;
    }

    @Override
    public void deleteMovie(Long id) throws Exception {      //Movie 테이블 삭제 delete
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();

            movieRepository.delete(movie);

        } else {
            throw new Exception();
        }
    }

    @Override
    public String  saveImgMovie(MovieImgDto movieDto, MultipartFile file) {     // 파일 저장

        Movie movie = new Movie();
        movie.setMovieName(movieDto.getMovieName());
        movie.setGenre(movieDto.getGenre());
        movie.setCompany(movieDto.getCompany());

        String uuidFile = fileOneSave(file); //
        if(uuidFile == null){
            movie.setMovieImg(null);
        }else{
            movie.setMovieImg("/images/"+ uuidFile); // 이미지 이름 저장시 반드시 /images(외부에 공개된 url임)추가해야 함
        }


        movieRepository.save(movie);

        return "Success";
    }

    public String fileOneSave(MultipartFile file) {     // 파일 설정
        String uuidFile = null;
        String fileName = file.getOriginalFilename();
        String fileExt = fileName.substring(fileName.lastIndexOf("."));
        log.info("UPLOAD_LOCATION : {}", UPLOAD_LOCATION); // C:/Images
        log.info("파일 이름 : {}", fileName);
        log.info("파일 확장자 : {}", fileExt); // .jpg 등
        log.info("파일 크기 : {}", file.getSize());
        uuidFile = UUID.randomUUID().toString().replaceAll("-", "") + fileExt; // .jpg
        log.info("UUID 파일명 : {}", uuidFile);
        String uploadFile = UPLOAD_LOCATION + "/" + uuidFile; // C:/Images/.jpg
        log.info("업로드 파일 : {}", uploadFile);
        try {
            if (file.isEmpty()) {
                throw new IOException("common.file.empty"); // 빈 파일입니다.
            }

            InputStream src = file.getInputStream();
            Path dest = Paths.get(uploadFile);
            log.info("src : {}", src);
            log.info("dest : {}", dest);
            Files.copy(src,dest, StandardCopyOption.REPLACE_EXISTING ); // java.nio.file.* 필요
        } catch (IOException e) {
            throw new RuntimeException("fileOne Save Error"+e.getMessage());
        }
        return uuidFile;
    }

    @Override
    public String saveNotImgMovie(MovieImgDto movieDto) {   // 파일 없이 저장
        Movie movie = new Movie();
        movie.setMovieName(movieDto.getMovieName());
        movie.setGenre(movieDto.getGenre());
        movie.setCompany(movieDto.getCompany());

        movieRepository.save(movie);

        return "Success";

    }

    @Override
    @Transactional
    public void deleteAll() throws Exception {          // 모두 삭제 TRUNCATE
        entityManager.createNativeQuery("TRUNCATE TABLE movie").executeUpdate();

        // 외래 키 제약 조건 해제
        String companyForeignKey = getForeignKeyConstraintName("movie", "company_number");
        entityManager.createNativeQuery("ALTER TABLE movie DROP FOREIGN KEY " + companyForeignKey).executeUpdate();

        String genreForeignKey = getForeignKeyConstraintName("movie", "genre_number");
        entityManager.createNativeQuery("ALTER TABLE movie DROP FOREIGN KEY " + genreForeignKey).executeUpdate();

        // 테이블 비우기
        entityManager.createNativeQuery("TRUNCATE TABLE genre").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE company").executeUpdate();

        // 외래 키 제약 조건 재설정               //제약 조건  확인
        entityManager.createNativeQuery("ALTER TABLE movie ADD CONSTRAINT FK8wh64d1y9e607mel9dwbat3yw " +
                "FOREIGN KEY (company_number) REFERENCES company(company_number)").executeUpdate();

        entityManager.createNativeQuery("ALTER TABLE movie ADD CONSTRAINT FKnbu9sm72i5xw5j0flh8y87aan " +
                "FOREIGN KEY (genre_number) REFERENCES genre(genre_number)").executeUpdate();
    }

    private String getForeignKeyConstraintName(String tableName, String columnName) {
        Query query = entityManager.createNativeQuery(
                "SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE " +
                        "WHERE TABLE_NAME = :tableName AND COLUMN_NAME = :columnName " +
                        "AND CONSTRAINT_NAME LIKE 'FK%'");
        query.setParameter("tableName", tableName);
        query.setParameter("columnName", columnName);
        return (String) query.getSingleResult();
    }



}
