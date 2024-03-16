package com.springboot.exam.controller.rest;


import com.springboot.exam.data.dto.CompanyDto.CompanyDto;
import com.springboot.exam.data.dto.CompanyDto.ResponseCompanyDto;
import com.springboot.exam.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyRestController {


    @Autowired
    CompanyService companyService;

    @PostMapping("/{name}")
    public ResponseEntity<ResponseCompanyDto> createCompany(@PathVariable String name) {       // Company 테이블에 값 추가 insert

        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyName(name);
        ResponseCompanyDto responseCompanyDto = companyService.saveCompany(companyDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCompanyDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CompanyDto>> getAllCompany(){                // Company 테이블 리스트 보기 select
        List<CompanyDto> listsDto =  companyService.selectAllCompany();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }

    @PutMapping("/{id}/{name}")
    public ResponseEntity<ResponseCompanyDto> updateCompany(            // Company 테이블 수정 update
        @PathVariable Long id,@PathVariable String name) throws Exception {

        ResponseCompanyDto responseCompanyDto = companyService.updateCompany(id, name);

        return ResponseEntity.status(HttpStatus.OK).body(responseCompanyDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) throws Exception {       // Company 테이블 삭제 delete
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }


}
