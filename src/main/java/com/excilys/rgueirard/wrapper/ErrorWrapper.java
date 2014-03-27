package com.excilys.rgueirard.wrapper;

public class ErrorWrapper {
	private String validIntroducedDate;
	private String validDiscontinuedDate;
	private String lesserThan;
	private String computerName;
	private String invalidChar;
	private boolean state;

	public ErrorWrapper() {
		super();
		validIntroducedDate = "";
		validDiscontinuedDate = "";
		lesserThan = "";
		computerName = "";
		invalidChar = "";
		state = false;
	}

	public String getValidIntroducedDate() {
		return validIntroducedDate;
	}

	public void setValidIntroducedDate(String validIntroducedDate) {
		this.validIntroducedDate = validIntroducedDate;
	}

	public String getValidDiscontinuedDate() {
		return validDiscontinuedDate;
	}

	public void setValidDiscontinuedDate(String validDiscontinuedDate) {
		this.validDiscontinuedDate = validDiscontinuedDate;
	}

	public String getLesserThan() {
		return lesserThan;
	}

	public void setLesserThan(String lesserThan) {
		this.lesserThan = lesserThan;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getInvalidChar() {
		return invalidChar;
	}

	public void setInvalidChar(String invalidChar) {
		this.invalidChar = invalidChar;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ErrorWrapper [validIntroducedDate=" + validIntroducedDate
				+ ", validDiscontinuedDate=" + validDiscontinuedDate
				+ ", lesserThan=" + lesserThan + ", computerName="
				+ computerName + ", invalidChar=" + invalidChar + ", state="
				+ state + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((computerName == null) ? 0 : computerName.hashCode());
		result = prime * result
				+ ((invalidChar == null) ? 0 : invalidChar.hashCode());
		result = prime * result
				+ ((lesserThan == null) ? 0 : lesserThan.hashCode());
		result = prime * result + (state ? 1231 : 1237);
		result = prime
				* result
				+ ((validDiscontinuedDate == null) ? 0 : validDiscontinuedDate
						.hashCode());
		result = prime
				* result
				+ ((validIntroducedDate == null) ? 0 : validIntroducedDate
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorWrapper other = (ErrorWrapper) obj;
		if (computerName == null) {
			if (other.computerName != null)
				return false;
		} else if (!computerName.equals(other.computerName))
			return false;
		if (invalidChar == null) {
			if (other.invalidChar != null)
				return false;
		} else if (!invalidChar.equals(other.invalidChar))
			return false;
		if (lesserThan == null) {
			if (other.lesserThan != null)
				return false;
		} else if (!lesserThan.equals(other.lesserThan))
			return false;
		if (state != other.state)
			return false;
		if (validDiscontinuedDate == null) {
			if (other.validDiscontinuedDate != null)
				return false;
		} else if (!validDiscontinuedDate.equals(other.validDiscontinuedDate))
			return false;
		if (validIntroducedDate == null) {
			if (other.validIntroducedDate != null)
				return false;
		} else if (!validIntroducedDate.equals(other.validIntroducedDate))
			return false;
		return true;
	}

	public static class Builder {
		ErrorWrapper errorWrapper;

		private Builder() {
			errorWrapper = new ErrorWrapper();
		}

		public Builder validIntroducedDate(String validIntroducedDate) {
			this.errorWrapper.validIntroducedDate = validIntroducedDate;
			return this;
		}
		
		public Builder validDiscontinuedDate(String validDiscontinuedDate) {
			this.errorWrapper.validDiscontinuedDate = validDiscontinuedDate;
			return this;
		}

		public Builder lesserThan(String lesserThan) {
			this.errorWrapper.lesserThan = lesserThan;
			return this;
		}

		public Builder computerName(String computerName) {
			this.errorWrapper.computerName = computerName;
			return this;
		}

		public Builder invalidChar(String invalidChar) {
			this.errorWrapper.invalidChar = invalidChar;
			return this;
		}
		
		public Builder state(boolean state) {
			this.errorWrapper.state = state;
			return this;
		}

		public ErrorWrapper build() {
			return this.errorWrapper;
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	/*
	 * "Please enter a valid date.\n"
	 * "Discontinued is earlier than introduced !\n"
	 * "Please enter a computer name.\n"
	 * "Invalid character \'<\', \'>\' or \'\"\' !"
	 */
}
