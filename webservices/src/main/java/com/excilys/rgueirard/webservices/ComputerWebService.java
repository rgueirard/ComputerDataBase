package com.excilys.rgueirard.webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.service.ComputerService;

@WebService
public class ComputerWebService {

	@Autowired
	private ComputerService computerService;

	@WebMethod
	public List<Computer> findAll() {
		return computerService.findAll();
	}

}
