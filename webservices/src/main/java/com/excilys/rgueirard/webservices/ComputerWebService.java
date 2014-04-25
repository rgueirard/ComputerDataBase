package com.excilys.rgueirard.webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.service.ComputerService;

@Component
@Path("/webService")
public class ComputerWebService {

	@Autowired
	private ComputerService computerService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Computer> findAll() {
		return computerService.findAll();
	}

}
