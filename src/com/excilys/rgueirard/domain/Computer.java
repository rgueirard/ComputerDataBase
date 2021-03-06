package com.excilys.rgueirard.domain;

import java.util.Date;

public class Computer {
	private long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Computer() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("\n-------------------\nId : ");
		sb.append(id);
		sb.append("\nName : ");
		sb.append(name);
		sb.append("\nIntroduced : ");
		sb.append(introduced);
		sb.append("\nDiscontinued : ");
		sb.append(discontinued);
		if(this.company != null) {
			sb.append("\ntCompany : ");
			sb.append("company.toString()");
		}
		sb.append("\n-------------------\n");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Computer other = (Computer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public static class Builder {
		private Computer computer;

		private Builder() {
			computer = new Computer();
		}

		public Builder id(long id) {
			this.computer.id = id;
			return this;
		}

		public Builder name(String name) {
			this.computer.name = name;
			return this;
		}

		public Builder introduced(Date introduced) {
			this.computer.introduced = introduced;
			return this;
		}

		public Builder discontinued(Date discontinued) {
			this.computer.discontinued = discontinued;
			return this;
		}

		public Builder company(Company company) {
			this.computer.company = company;
			return this;
		}

		public Computer build() {
			return this.computer;
		}

	}

	public static Builder builder() {
		return new Builder();
	}

}
