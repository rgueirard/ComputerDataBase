package com.excilys.rgueirard.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.domain.ComputerDTO;
import com.excilys.rgueirard.service.CompanyService;

public class ComputerMapper {

	public static ComputerDTO computerToDTO(Computer computer) {
		ComputerDTO computerDTO = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String introduced = null;
		String discontinued = null;
		long companyId = 0;
		String companyName = null;
		
		if (computer.getIntroduced() != null) {
			introduced = formatter.format(computer.getIntroduced());
		}
		if (computer.getDiscontinued() != null) {
			discontinued = formatter.format(computer.getDiscontinued());
		}
		if (computer.getCompany() != null) {
			companyId = computer.getCompany().getId();
			companyName = computer.getCompany().getName();
		}
		computerDTO = ComputerDTO.builder().id(computer.getId())
				.name(computer.getName()).introduced(introduced)
				.discontinued(discontinued).companyId(companyId).companyName(companyName).build();

		return computerDTO;
	}

	public static Computer DTOToComputer(ComputerDTO computerDTO)
			throws ParseException {
		Computer computer = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date introduced = null;
		Date discontinued = null;
		Company company = null;

		if ((computerDTO.getIntroduced() != null)
				&& (computerDTO.getIntroduced() != "")) {
			introduced = formatter.parse(computerDTO.getIntroduced());
		}
		if ((computerDTO.getDiscontinued() != null)
				&& (computerDTO.getDiscontinued() != "")) {
			discontinued = formatter.parse(computerDTO.getDiscontinued());
		}
		if (computerDTO.getCompanyId() != 0) {
			DataBaseManager dataBaseManager = DataBaseManager.getInstance();
			CompanyService companyService = dataBaseManager.getCompanyService();
			company = companyService.retrieve(computerDTO.getCompanyId());
		}

		computer = Computer.builder().id(computerDTO.getId())
				.name(computerDTO.getName()).introduced(introduced)
				.discontinued(discontinued).company(company).build();

		return computer;
	}
}
