package com.task.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.task.model.Company;

public interface CompanyService {

    Company getCompanyById(long id);

    Page<Company> getCompanyList(int pageNo, int pageSize, String sortBy);

    ResponseEntity<Company> createCompany(Company company);

    ResponseEntity<String> updateExistingCompany(long id, Company company);

    ResponseEntity<String> deleteExistingCompany(long id);

}
