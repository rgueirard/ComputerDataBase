package com.excilys.rgueirard.domain;

public class ComputerDTO {
	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private long companyId;
	private String companyName;

	public ComputerDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "\n-------------------" + "\nId : " + id + "\nName : " + name
				+ "\nIntroduced : " + introduced + "\nDiscontinued : "
				+ discontinued + "\nCompany : \n\tCompany Id : " + companyId
				+ "\n\tCompany name : " + companyName
				+ "\n-------------------\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result
				+ ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ComputerDTO other = (ComputerDTO) obj;
		if (companyId != other.companyId)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static class Builder {
		private ComputerDTO computerDTO;

		private Builder() {
			computerDTO = new ComputerDTO();
		}

		public Builder id(long id) {
			this.computerDTO.id = id;
			return this;
		}

		public Builder name(String name) {
			this.computerDTO.name = name;
			return this;
		}

		public Builder introduced(String introduced) {
			this.computerDTO.introduced = introduced;
			return this;
		}

		public Builder discontinued(String discontinued) {
			this.computerDTO.discontinued = discontinued;
			return this;
		}

		public Builder companyId(long companyId) {
			this.computerDTO.companyId = companyId;
			return this;
		}

		public Builder companyName(String companyName) {
			this.computerDTO.companyName = companyName;
			return this;
		}

		public ComputerDTO build() {
			return this.computerDTO;
		}

	}

	public static Builder builder() {
		return new Builder();
	}
}
