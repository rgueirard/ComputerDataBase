package com.excilys.rgueirard.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.rgueirard.dto.ComputerDTO;

@Component
public class ComputerValidator implements Validator{

	public boolean validDate(String date) {
		boolean error = true;
		String regexpDate = "[0-9]{4}[-](0?[1-9]|1[012])[-](0?[1-9]|[12][0-9]|3[01])";
		if (date.matches(regexpDate)) {
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));

			if ((year > 999) && (year < 10000)) {
				if ((month > 0) && (month < 13)) {
					if (day > 0) {
						if ((month == 12) && (day <= 31)) {
							error = false;
						} else if ((month == 11) && (day <= 30)) {
							error = false;
						} else if ((month == 10) && (day <= 31)) {
							error = false;
						} else if ((month == 9) && (day <= 30)) {
							error = false;
						} else if ((month == 8) && (day <= 31)) {
							error = false;
						} else if ((month == 7) && (day <= 31)) {
							error = false;
						} else if ((month == 6) && (day <= 30)) {
							error = false;
						} else if ((month == 5) && (day <= 31)) {
							error = false;
						} else if ((month == 4) && (day <= 30)) {
							error = false;
						} else if ((month == 3) && (day <= 31)) {
							error = false;
						} else if ((month == 2)
								&& ((((year % 4) == 0) && (day <= 29)) || (((year % 4) != 0) && (day <= 28)))) {
							error = false;
						} else if ((month == 1) && (day <= 31)) {
							error = false;
						}
					}
				}
			}
		}
		return error;
	}

	private boolean discontinuedLTIntroduced(String introduced,
			String discontinued) {

		boolean error = false;

		int yearIntroduced = Integer.parseInt(introduced.substring(0, 4));
		int monthIntroduced = Integer.parseInt(introduced.substring(5, 7));
		int dayIntroduced = Integer.parseInt(introduced.substring(8, 10));
		int yearDiscontinued = Integer.parseInt(discontinued.substring(0, 4));
		int monthDiscontinued = Integer.parseInt(discontinued.substring(5, 7));
		int dayDiscontinued = Integer.parseInt(discontinued.substring(8, 10));

		if (yearDiscontinued < yearIntroduced) {
			error = true;
		} else if (yearDiscontinued == yearIntroduced) {
			if (monthDiscontinued < monthIntroduced) {
				error = true;
			} else if (monthDiscontinued == monthIntroduced) {
				if (dayDiscontinued < dayIntroduced) {
					error = true;
				}
			}
		}

		return error;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return ComputerDTO.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Boolean dateError = false;
		ComputerDTO computerDTO = (ComputerDTO) arg0;
		String regexpName = ".*[<|>|\"]+.*";
					
		if ((computerDTO.getIntroduced().length() > 0)
				&& (validDate(computerDTO.getIntroduced()))) {
			arg1.rejectValue("introduced", "cptDTO.int","Please enter a valid introduced date.\n");
			
			dateError = true;
		}

		if ((computerDTO.getDiscontinued().length() > 0)
				&& (validDate(computerDTO.getDiscontinued()))) {
			arg1.rejectValue("discontinued", "cptDTO.disc", "Please enter a valid discontinued date.\n");
			
			dateError = true;
		}

		if ((dateError == false)
				&& (!validDate(computerDTO.getIntroduced()))
				&& (!validDate(computerDTO.getDiscontinued()))) {
			if (discontinuedLTIntroduced(computerDTO.getIntroduced(),
					computerDTO.getDiscontinued())) {
				arg1.rejectValue("discontinued", "cptDTO.lt", "Discontinued date is earlier than introduced date !\n");
				
			}
		}

		if (computerDTO.getName().trim().length() == 0) {
			arg1.rejectValue("name", "cptDTO.name", "Please enter a computer name.\n");
			

		} else {
			if (computerDTO.getName().matches(regexpName)) {
				arg1.rejectValue("name", "cptDTO.inv", "Invalid character \'<\', \'>\' or \'\"\' !\n");
				
			}
		}
	}
}
