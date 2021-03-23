package com.task.service.implementation;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.task.model.Company;
import com.task.repository.CompanyRepository;
import com.task.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class CompanyServiceImplementation implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public Company getCompanyById(long id) {
        log.info("trying to get company by id");
        return companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect company id " + id + " attribute!"));
    }

    @Override
    public Page<Company> getCompanyList(int page, int size, String sort) {
        String[] sorterParams = sort.split(",");
        String sortField = sorterParams[0];
        String sortMethod = sorterParams[1];
        Sort sorter = sortMethod.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField)
                : Sort.by(sortField).descending();
        return companyRepository.findAll(PageRequest.of(page, size, sorter));
    }

    @Override
    public ResponseEntity<Company> createCompany(Company company) {
        log.info("trying to save company with attributes: tittle {}, founded {}",
                company.getTitle(),
                company.getFounded());
        return new ResponseEntity<>(companyRepository.save(company), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateExistingCompany(long id, Company company) {
        if (companyRepository.existsById(id)) {
            log.debug("trying to update existing company with id {}", id);
            int updatedRowCount = companyRepository.updateById(company.getTitle(), company.getFounded(), id);
            log.debug("updated row count = {}", updatedRowCount);
            return new ResponseEntity<>("company with id " + id + " was updated successfully!", HttpStatus.OK);
        }
        throw new IllegalArgumentException("Incorrect company id attribute!");
    }

    @Override
    public ResponseEntity<String> deleteExistingCompany(long id) {
        if (id <= 0 || !companyRepository.existsById(id)) {
            throw new IllegalArgumentException("Incorrect company id attribute!");
        }
        log.debug("trying to delete company with id {}", id);
        companyRepository.deleteById(id);
        return new ResponseEntity<>("company with id " + id + " deleted!", HttpStatus.OK);
    }

}
