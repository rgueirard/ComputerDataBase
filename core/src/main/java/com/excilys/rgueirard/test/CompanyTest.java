package com.excilys.rgueirard.test;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.rgueirard.domain.Company;

public class CompanyTest {

	private static final Logger logger = LoggerFactory.getLogger(CompanyTest.class);
	
	public static void main(String[] args) {
	
		Company company = new Company();
		company.setId(0);
		company.setName("Excilys");
			
		logger.info("{}\n", company.toString());
	}

}
