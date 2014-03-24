package com.excilys.rgueirard.controller;

import com.excilys.rgueirard.domain.ComputerDTO;
import com.excilys.rgueirard.domain.ErrorWrapper;

public class ComputerValidator {

	public static ErrorWrapper validate(ComputerDTO computerDTO) {
		ErrorWrapper error = new ErrorWrapper();
		Boolean dateError = false;
		String regexpName = ".*[<|>|\"]+.*";

		if ((computerDTO.getIntroduced().length() > 0)
				&& (validDate(computerDTO.getIntroduced()))) {
			error.setValidIntroducedDate("Please enter a valid introduced date.\n");
			error.setState(true);
			dateError = true;
		}

		if ((computerDTO.getDiscontinued().length() > 0)
				&& (validDate(computerDTO.getDiscontinued()))) {
			error.setValidDiscontinuedDate("Please enter a valid discontinued date.\n");
			error.setState(true);
			dateError = true;
		}

		if ((dateError == false)
				&& (computerDTO.getIntroduced().length() == 10)
				&& (computerDTO.getDiscontinued().length() == 10)) {
			if (discontinuedLTIntroduced(computerDTO.getIntroduced(),
					computerDTO.getDiscontinued())) {
				error.setLesserThan("Discontinued date is earlier than introduced date !\n");
				error.setState(true);
			}
		}

		if (computerDTO.getName().trim().length() == 0) {
			error.setComputerName("Please enter a computer name.\n");
			error.setState(true);

		} else {
			if (computerDTO.getName().matches(regexpName)) {
				error.setInvalidChar("Invalid character \'<\', \'>\' or \'\"\' !\n");
				error.setState(true);
			}
		}
		return error;
	}

	public static boolean validDate(String date) {
		boolean error = true;
		if (date.length() == 10) {
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

	private static boolean discontinuedLTIntroduced(String introduced,
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
}
