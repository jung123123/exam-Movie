package com.springboot.exam.service.impl;

import com.springboot.exam.data.dto.CompanyDto.CompanyDto;
import com.springboot.exam.data.dto.CompanyDto.ResponseCompanyDto;
import com.springboot.exam.data.entity.Company;
import com.springboot.exam.data.repository.CompanyRepository;
import com.springboot.exam.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public  CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }


    @Override
    public List<CompanyDto> selectAllCompany() {         //Company 테이블에 값 추가 select
        List<Company> list = companyRepository.findAll();

        List<CompanyDto> listDto = new ArrayList<>();

        for (Company c : list){
            CompanyDto companyDto = new CompanyDto();
            companyDto.setCompanyNumber(c.getCompanyNumber());
            companyDto.setCompanyName(c.getCompanyName());

            listDto.add(companyDto);

        }

        return listDto;
    }

    @Override
    public ResponseCompanyDto saveCompany(CompanyDto companyDto) {  //Company 테이블에 값 추가 insert
        Company company = new Company();

        company.setCompanyName(companyDto.getCompanyName());

        Company saveCompany = companyRepository.save(company);

        ResponseCompanyDto responseCompanyDto = new ResponseCompanyDto();
        responseCompanyDto.setCompanyNumber(saveCompany.getCompanyNumber());
        responseCompanyDto.setCompanyName(saveCompany.getCompanyName());

        return responseCompanyDto;
    }

    @Override
    public String saveCompanyName(String name) {    //Company 추가
        Company company = new Company();

        company.setCompanyName(name);

        companyRepository.save(company);

        return "Success";
    }

    @Override
    public ResponseCompanyDto updateCompany(Long id, String name) throws Exception {     //Company 테이블 수정 update
        Optional<Company> companyOptional = companyRepository.findById(id);

        Company updateCompany;
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();

            company.setCompanyName(name);

            updateCompany = companyRepository.save(company);
        } else {
            throw new Exception();
        }

        ResponseCompanyDto responseCompanyDto =  new ResponseCompanyDto();
        responseCompanyDto.setCompanyNumber(updateCompany.getCompanyNumber());
        responseCompanyDto.setCompanyName(updateCompany.getCompanyName());

        return responseCompanyDto;
    }

    @Override
    public void deleteCompany(Long id) throws Exception {        //Company 테이블 삭제 delete
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();

            companyRepository.delete(company);

        } else {
            throw new Exception();
        }
    }
}
