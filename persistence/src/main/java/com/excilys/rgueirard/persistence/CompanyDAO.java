package com.excilys.rgueirard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.rgueirard.domain.Company;

public interface CompanyDAO extends JpaRepository<Company, Long> {
}
