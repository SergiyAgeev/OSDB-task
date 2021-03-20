package com.task.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Company;
import com.task.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {
	private final CompanyService companyService;

	@ApiOperation(value = "Get all companies, you can modify result by using params:" +
			" startPage; pageSize; sortBy, sortMethod(asc, desc)")
	@GetMapping
	public ResponseEntity<List<Company>> get(@RequestParam(defaultValue = "0", required = false) int page,
											 @RequestParam(defaultValue = "20", required = false) int size,
											 @RequestParam(defaultValue = "id,desc", required = false) String sort) {
		Page<Company> paged = companyService.getCompanyList(page, size, sort);
		return new ResponseEntity<>(paged.getContent(), new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get single company by company id", response = Company.class)
	@GetMapping(value = "/{companyId}")
	public Company getSingleCompany(@PathVariable("companyId") long companyId) {
		return companyService.getCompanyById(companyId);
	}

	@ApiOperation(value = "Create company", response = Company.class)
	@PostMapping
	public ResponseEntity<Company> createCompany(@RequestBody Company company) {
		return companyService.createCompany(company);
	}

	@ApiOperation(value = "Update company by id", response = Company.class)
	@PutMapping(value = "/{companyId}")
	public ResponseEntity<String> updateCompany(@PathVariable("companyId") long companyId,
												@RequestBody Company company) {
		return companyService.updateExistingCompany(companyId, company);
	}

	@ApiOperation(value = "Delete company by id")
	@DeleteMapping(value = "/{companyId}")
	public ResponseEntity<String> deleteCompany(@PathVariable("companyId") long companyId) {
		return companyService.deleteExistingCompany(companyId);
	}

}
