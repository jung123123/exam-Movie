package com.springboot.exam.service;

import com.springboot.exam.data.dto.CompanyDto.CompanyDto;
import com.springboot.exam.data.dto.CompanyDto.ResponseCompanyDto;


import java.util.List;

public interface CompanyService {

    List<CompanyDto> selectAllCompany();  //Company 테이블에 값 추가 select

    ResponseCompanyDto saveCompany(CompanyDto companyDto);  //Company 테이블에 값 추가 insert

    String saveCompanyName(String name);        //Company 테이블에 CompanyName 값 추가 insert

    ResponseCompanyDto updateCompany(Long id, String name) throws Exception;        //Company 테이블 수정 update

    void deleteCompany(Long id) throws Exception;       //Company 테이블 삭제 delete

}
