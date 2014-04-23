package com.excilys.rgueirard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Log;

@Repository
public interface LogDAO extends JpaRepository<Log,Long>{
}
