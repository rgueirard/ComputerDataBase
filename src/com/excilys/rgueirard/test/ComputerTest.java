package com.excilys.rgueirard.test;

import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;

public class ComputerTest {
	private static Logger logger = LoggerFactory.getLogger(ComputerTest.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		Company company = Company.builder().id(0).name("excilys").build();

		Computer computer1 = Computer.builder().id(1).name("cmpt1")
				.introduced(new Date()).discontinued(new Date())
				.company(company).build();

		Computer computer2 = Computer.builder().id(2).name("cmpt2")
				.introduced(null).discontinued(null).company(company).build();
		
		
		logger.info("computer 1 : {}\n", computer1.toString());
		logger.info("computer 2 : {}\n", computer2.toString());
	}

}
