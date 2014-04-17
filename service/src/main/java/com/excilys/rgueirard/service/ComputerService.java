package com.excilys.rgueirard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.persistence.ComputerDAO;
import com.excilys.rgueirard.persistence.LogDAO;
import com.excilys.rgueirard.wrapper.PageWrapper;

@Service
@Transactional(readOnly = true)
public class ComputerService {

	private static Logger logger = LoggerFactory
			.getLogger(ComputerService.class);

	@Autowired
	private ComputerDAO computerDAO;

	@Autowired
	private LogService logService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private LogDAO logDAO;

	public ComputerService() {
		super();
	}

	@Transactional(readOnly = false)
	public void create(Computer computer) {
		long id = 0;

		logger.info("ComputerService : creation d'ordinateur");
		id = computerDAO.create(computer);
		logDAO.create(id, "create");

	}

	@Transactional(readOnly = false)
	public void update(Computer computer) {
		long id = 0;

		logger.info("ComputerService : edition d'ordinateur");
		id = computerDAO.update(computer);
		logDAO.create(id, "update");

	}

	@Transactional(readOnly = false)
	public void delete(long pId) {
		long id = 0;

		logger.info("ComputerService : deletion d'ordinateur");
		id = computerDAO.delete(pId);
		logDAO.create(id, "delete");

	}

	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper) {

		logger.info("ComputerService : rechercher d'ordinateurs");
		wrapper = computerDAO.retrieve(wrapper);

		return wrapper;
	}

	public Computer retrieveById(long id) {
		Computer computer = null;

		logger.info("ComputerService : rechercher d'ordinateur par id");
		computer = computerDAO.retrieveById(id);

		return computer;
	}
}