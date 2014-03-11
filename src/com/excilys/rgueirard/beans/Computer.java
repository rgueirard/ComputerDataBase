package com.excilys.rgueirard.beans;

import java.util.Date;

public class Computer {
	private static int cptId = 574;
	private long id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private long companyId;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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
		this.id = Computer.cptId + 1;
		Computer.cptId = Computer.cptId + 1;
	}

	public Computer(long id, String name, Date introduced, Date discontinued,
			long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	public Computer(String name, Date introduced, Date discontinued, long companyId) {
		super();
		this.id = Computer.cptId + 1;
		Computer.cptId = Computer.cptId + 1;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "\n\tId : " + id + "\n\tName : " + name + "\n\tIntroduced : "
				+ introduced + "\n\tDiscontinued : " + discontinued + "\n\tCompany : " + companyId + "\n";
	}

}
