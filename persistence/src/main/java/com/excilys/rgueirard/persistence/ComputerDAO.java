package com.excilys.rgueirard.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.rgueirard.domain.Computer;

public interface ComputerDAO extends JpaRepository<Computer, Long>{
	Page<Computer> findByNameContaining(String name, Pageable pageable);
	Page<Computer> findByCompanyNameContaining(String companyName, Pageable pageable);
}
