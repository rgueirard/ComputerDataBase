package com.excilys.rgueirard.mapper;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.dto.ComputerDTO;
import com.excilys.rgueirard.persistence.DataBaseManager;
import com.excilys.rgueirard.service.CompanyService;

@Component
public class ComputerMapper {

	@Autowired
	private DataBaseManager dataBaseManager;
	
	@Autowired
	private CompanyService companyService;
	
	public ComputerDTO computerToDTO(Computer computer) {
		ComputerDTO computerDTO = null;
		String introduced = null;
		String discontinued = null;
		long companyId = 0;
		String companyName = null;

		if (computer.getIntroduced() != null) {
			introduced = ISODateTimeFormat.date().print(computer.getIntroduced());
		}
		if (computer.getDiscontinued() != null) {
			discontinued = ISODateTimeFormat.date().print(computer.getDiscontinued());
		}
		if (computer.getCompany() != null) {
			companyId = computer.getCompany().getId();
			companyName = computer.getCompany().getName();
		}
		computerDTO = ComputerDTO.builder().id(computer.getId())
				.name(computer.getName()).introduced(introduced)
				.discontinued(discontinued).companyId(companyId)
				.companyName(companyName).build();

		return computerDTO;
	}

	public Computer DTOToComputer(ComputerDTO computerDTO) {
		Computer computer = null;
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime introduced = null;
		DateTime discontinued = null;
		Company company = null;
		if ((computerDTO.getIntroduced() != null)
				&& (computerDTO.getIntroduced() != "")) {

			introduced = DateTime.parse(computerDTO.getIntroduced(), formatter);

		}
		if ((computerDTO.getDiscontinued() != null)
				&& (computerDTO.getDiscontinued() != "")) {
			discontinued = DateTime.parse(computerDTO.getDiscontinued(), formatter);
		}
		if (computerDTO.getCompanyId() != 0) {
			company = companyService.retrieve(computerDTO.getCompanyId());
		}

		computer = Computer.builder().id(computerDTO.getId())
				.name(computerDTO.getName()).introduced(introduced)
				.discontinued(discontinued).company(company).build();

		return computer;
	}
}
