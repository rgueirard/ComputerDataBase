package com.excilys.rgueirard.domain;

public class Company {
	private long id;
	private String name;

	public Company() {
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

	@Override
	public String toString() {
		return "\n\tId : " + id + "\n\tName : " + name;
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public static class Builder {
		Company company;

		private Builder() {
			company = new Company();
		}

		public Builder id(long id) {
			this.company.id = id;
			return this;
		}

		public Builder name(String name) {
			this.company.name = name;
			return this;
		}

		public Company build() {
			return this.company;
		}
	}

	public static Builder builder() {
		return new Builder();
	}
	
}
