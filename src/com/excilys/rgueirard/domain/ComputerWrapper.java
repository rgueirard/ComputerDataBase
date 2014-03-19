package com.excilys.rgueirard.domain;

import java.util.List;

public class ComputerWrapper {
	private Computer computer;
	private List<Computer> computers;
	private int size;
	
	public ComputerWrapper() {
		super();
		computer = null;
		computers = null;
		size = 0;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ComputerWrapper [computer=" + computer + ", computers="
				+ computers + ", size=" + size + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((computer == null) ? 0 : computer.hashCode());
		result = prime * result
				+ ((computers == null) ? 0 : computers.hashCode());
		result = prime * result + size;
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
		ComputerWrapper other = (ComputerWrapper) obj;
		if (computer == null) {
			if (other.computer != null)
				return false;
		} else if (!computer.equals(other.computer))
			return false;
		if (computers == null) {
			if (other.computers != null)
				return false;
		} else if (!computers.equals(other.computers))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	
	public static class Builder {
		ComputerWrapper wrapper;

		private Builder() {
			wrapper = new ComputerWrapper();
		}

		public Builder size(int size) {
			this.wrapper.size = size;
			return this;
		}

		public Builder computer(Computer computer) {
			this.wrapper.computer = computer;
			return this;
		}
		
		public Builder computers(List<Computer> computers) {
			this.wrapper.computers = computers;
			return this;
		}

		public ComputerWrapper build() {
			return this.wrapper;
		}
	}

	public static Builder builder() {
		return new Builder();
	}
	
	
}
