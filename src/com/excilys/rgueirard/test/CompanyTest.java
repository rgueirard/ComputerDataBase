package com.excilys.rgueirard.test;

import com.excilys.rgueirard.domain.Company;

public class CompanyTest {

	public static void main(String[] args) {
		Company company = new Company();
		company.setId(0);
		company.setName("Excilys");
			
		System.out.println(company.toString());
	}

}
