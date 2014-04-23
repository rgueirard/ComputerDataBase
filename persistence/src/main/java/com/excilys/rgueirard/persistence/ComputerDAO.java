package com.excilys.rgueirard.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Computer;

@Repository
public interface ComputerDAO extends JpaRepository<Computer, Long>{
	Page<Computer> findByNameContainingOrCompanyNameContaining(String name, String companyName, Pageable pageable);
}
