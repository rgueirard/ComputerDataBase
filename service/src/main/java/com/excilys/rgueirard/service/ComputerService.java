package com.excilys.rgueirard.service;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.domain.Log;
import com.excilys.rgueirard.persistence.ComputerDAO;
import com.excilys.rgueirard.persistence.LogDAO;

@Service
@Transactional(readOnly = true)
public class ComputerService {

	private static Logger logger = LoggerFactory
			.getLogger(ComputerService.class);

	@Autowired
	private ComputerDAO computerDAO;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private LogDAO logDAO;

	public ComputerService() {
		super();
	}

	@Transactional(readOnly = false)
	public void create(Computer computer) {
		Computer result = null;
		DateTime time = new DateTime();
		Log log = null;


		logger.info("ComputerService : creation d'ordinateur");
		
		result = computerDAO.save(computer);
		log = Log.builder().computerId(result.getId()).message("create").time(time).build();
		logDAO.save(log);

	}

	@Transactional(readOnly = false)
	public void update(Computer computer) {
		Computer result = null;
		DateTime time = new DateTime();
		Log log = null;
		
		logger.info("ComputerService : edition d'ordinateur");
		
		result = computerDAO.save(computer);
		log = Log.builder().computerId(result.getId()).message("update").time(time).build();
		logDAO.save(log);

	}

	@Transactional(readOnly = false)
	public void delete(long pId) {
		DateTime time = new DateTime();
		Log log = null;
		
		logger.info("ComputerService : deletion d'ordinateur");
		computerDAO.delete(pId);
		log = Log.builder().computerId(pId).message("delete").time(time).build();
		logDAO.save(log);
	}

	public Page<Computer> retrieveByName(String searchMotif, Pageable pageable) {

		logger.info("ComputerService : rechercher d'ordinateurs");
		return computerDAO.findByNameContaining(searchMotif, pageable);
	}
	
	public Page<Computer> retrieveByCompanyName(String searchMotif, Pageable pageable) {

		logger.info("ComputerService : rechercher d'ordinateurs");
		return computerDAO.findByCompanyNameContaining(searchMotif, pageable);
	}
	
	public Computer retrieveById(long id) {
		Computer computer = null;

		logger.info("ComputerService : rechercher d'ordinateur par id");
		computer = computerDAO.findOne(id);

		return computer;
	}

	public Page<Computer> findAll(Pageable pageable) {
		return computerDAO.findAll(pageable);
	}
	
	public List<Computer> findAll() {
		return computerDAO.findAll();
	}
}