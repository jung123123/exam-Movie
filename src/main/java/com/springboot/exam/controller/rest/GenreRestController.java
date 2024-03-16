package com.springboot.exam.controller.rest;



import com.springboot.exam.data.dto.GenreDto.GenreDto;
import com.springboot.exam.data.dto.GenreDto.ResponseGenreDto;
import com.springboot.exam.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreRestController {

    @Autowired
    GenreService genreService;


    @PostMapping("/{name}")
    public ResponseEntity<ResponseGenreDto> createGenre(@PathVariable String name) {       // Genre 테이블에 값 추가 insert

        GenreDto genreDto = new GenreDto();
        genreDto.setGenreName(name);
        ResponseGenreDto responseCompanyDto = genreService.saveGenre(genreDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCompanyDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GenreDto>> getAllGenre(){                // Genre 테이블 리스트 보기 select
        List<GenreDto> listsDto =  genreService.selectAllGenre();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }

    @PutMapping("/{id}/{name}")
    public ResponseEntity<ResponseGenreDto> updateGenre(            // Genre 테이블 수정 update
        @PathVariable Long id,@PathVariable String name) throws Exception {

        ResponseGenreDto responseGenreDto = genreService.updateGenre(id, name);

        return ResponseEntity.status(HttpStatus.OK).body(responseGenreDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Long id) throws Exception {       // Genre 테이블 삭제 delete
        genreService.deleteGenre(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
