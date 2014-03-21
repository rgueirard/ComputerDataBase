package com.excilys.rgueirard.controller;

import com.excilys.rgueirard.domain.ComputerDTO;

public class ComputerValidator {

	public static String validate(ComputerDTO computerDTO) {
		StringBuilder error = new StringBuilder("");
		String regexpName = ".*[<|>|\"]*.*";

		Boolean datesHaveNoError = true;
		if (computerDTO.getIntroduced().length() > 0
				&& !testDate(computerDTO.getIntroduced())) {
			error.append("Please enter a valid date.");
			datesHaveNoError = false;
		}

		if (computerDTO.getDiscontinued().length() > 0
				&& !testDate(computerDTO.getDiscontinued())) {
			error.append("Please enter a valid date.");
			datesHaveNoError = false;
		}

		if (datesHaveNoError && computerDTO.getIntroduced().length() == 10
				&& computerDTO.getIntroduced().length() == 10) {
			if (!discontinuedLTIntroduced(computerDTO.getDiscontinued(),
					computerDTO.getIntroduced())) {
				error.append("Discontinued is earlier than introduced !");
			}
		}
		
		if (computerDTO.getName().trim().length() == 0) {
			error.append("Please enter a computer name.");
		} else {
			if (computerDTO.getName().matches(regexpName)){
				error.append("Invalid character \'<\', \'>\' or \'\"\' !");
			}
		}
		
		return error.toString();
	}

	private static boolean discontinuedLTIntroduced(String discontinued,
			String introduced) {

		boolean error = false;

		int yearDiscontinued = Integer.parseInt(discontinued.substring(0, 4));
		int monthDiscontinued = Integer.parseInt(discontinued.substring(5, 7));
		int dayDiscontinued = Integer.parseInt(discontinued.substring(8, 10));
		int yearIntroduced = Integer.parseInt(introduced.substring(0, 4));
		int monthIntroduced = Integer.parseInt(introduced.substring(5, 7));
		int dayIntroduced = Integer.parseInt(introduced.substring(8, 10));

		if (yearDiscontinued > yearIntroduced) {
			error = true;
		} else if (yearDiscontinued == yearIntroduced) {
			if (monthDiscontinued > monthIntroduced) {
				error = true;
			} else if (monthDiscontinued == monthIntroduced) {
				if (dayDiscontinued > dayIntroduced) {
					error = true;
				}
			}
		}

		return error;
	}

	public static boolean testDate(String date) {
		boolean error = false;
		if (date.length() == 10) {
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));

			if ((year > 999) && (year < 10000)) {
				if ((month > 0) && (month < 13)) {
					if (day > 0) {
						if ((month == 12)&&(day <= 31)) {
							error = true;
						} else if ((month == 11)&&(day <= 30)) {
							error = true;
						} else if ((month == 10)&&(day <= 31)) {
							error = true;
						} else if ((month == 9)&&(day <= 30)) {
							error = true;
						} else if ((month == 8)&&(day <= 31)) {
							error = true;
						} else if ((month == 7)&&(day <= 31)) {
							error = true;
						} else if ((month == 6)&&(day <= 30)) {
							error = true;
						} else if ((month == 5)&&(day <= 31)) {
							error = true;
						} else if ((month == 4)&&(day <= 30)) {
							error = true;
						} else if ((month == 3)&&(day <= 31)) {
							error = true;
						} else if ((month == 2)
								&& ((((year % 4) == 0) && (day <= 29)) || (((year % 4) != 0) && (day <= 28)))) {
							error = true;
						} else if ((month == 1)&&(day <= 31)) {
							error = true;
						}
					}
				}
			}
		}
		return error;
	}

}
