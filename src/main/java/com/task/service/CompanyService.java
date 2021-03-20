package com.task.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.task.model.Company;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company getCompanyById(long id);

    Page<Company> getCompanyList(int pageNo, int pageSize, String sortBy);

    ResponseEntity<Company> createCompany(Company company);

    ResponseEntity<String> updateExistingCompany(long id, Company company);

    ResponseEntity<String> deleteExistingCompany(long id);

}
