package com.excilys.rgueirard.domain;

import java.util.ArrayList;
import java.util.List;

public class DashboardWrapper {
	private int size;
	private int nbDisplay;
	private int nbPages;
	private int currentPage;
	private List<Computer> computers;
	private int orderBy;
	private int searchType;
	private String searchMotif;

	public DashboardWrapper() {
		super();
		computers = new ArrayList<Computer>();
		size = -1;
		nbDisplay = 1;
		nbPages = -1;
		currentPage = 1;
		orderBy = 1;
		searchType = 0;
		searchMotif = "";
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNbDisplay() {
		return nbDisplay;
	}

	public void setNbDisplay(int nbDisplay) {
		this.nbDisplay = nbDisplay;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Computer> getComputers() {
		return computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}
	
	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}
	
	public String getSearchMotif() {
		return searchMotif;
	}

	public void setSearchMotif(String searchMotif) {
		this.searchMotif = searchMotif;
	}

	@Override
	public String toString() {
		return "DashboardWrapper [size=" + size + ", nbDisplay=" + nbDisplay
				+ ", nbPages=" + nbPages + ", currentPage=" + currentPage
				+ ", computers=" + computers + ", orderBy=" + orderBy
				+ ", searchType=" + searchType + ", searchMotif=" + searchMotif
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((computers == null) ? 0 : computers.hashCode());
		result = prime * result + currentPage;
		result = prime * result + nbDisplay;
		result = prime * result + nbPages;
		result = prime * result + orderBy;
		result = prime * result
				+ ((searchMotif == null) ? 0 : searchMotif.hashCode());
		result = prime * result + searchType;
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
		DashboardWrapper other = (DashboardWrapper) obj;
		if (computers == null) {
			if (other.computers != null)
				return false;
		} else if (!computers.equals(other.computers))
			return false;
		if (currentPage != other.currentPage)
			return false;
		if (nbDisplay != other.nbDisplay)
			return false;
		if (nbPages != other.nbPages)
			return false;
		if (orderBy != other.orderBy)
			return false;
		if (searchMotif == null) {
			if (other.searchMotif != null)
				return false;
		} else if (!searchMotif.equals(other.searchMotif))
			return false;
		if (searchType != other.searchType)
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	public static class Builder {
		DashboardWrapper dashboardWrapper;

		private Builder() {
			dashboardWrapper = new DashboardWrapper();
		}

		public Builder size(int size) {
			this.dashboardWrapper.size = size;
			return this;
		}

		public Builder nbDisplay(int nbDisplay) {
			this.dashboardWrapper.nbDisplay = nbDisplay;
			return this;
		}

		public Builder nbPages(int nbPages) {
			this.dashboardWrapper.nbPages = nbPages;
			return this;
		}

		public Builder currentPage(int currentPage) {
			this.dashboardWrapper.currentPage = currentPage;
			return this;
		}

		public Builder computers(List<Computer> computers) {
			this.dashboardWrapper.computers = computers;
			return this;
		}
		
		public Builder orderBy(int orderBy) {
			this.dashboardWrapper.orderBy = orderBy;
			return this;
		}
		
		public Builder searchType(int searchType) {
			this.dashboardWrapper.searchType = searchType;
			return this;
		}
		
		public Builder searchMotif(String searchMotif) {
			this.dashboardWrapper.searchMotif = searchMotif;
			return this;
		}

		public DashboardWrapper build() {
			return this.dashboardWrapper;
		}
	}

	public static Builder builder() {
		return new Builder();
	}

}
