package com.task.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.task.model.Company;
import java.time.LocalDate;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
    @Modifying
    @Query(value = "UPDATE Company c SET c.title =?1, c.founded =?2 WHERE c.id =?3")
    int updateById(String title, LocalDate founded, long id);

}
