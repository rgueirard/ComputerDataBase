package com.excilys.rgueirard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Company;

@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {
}
