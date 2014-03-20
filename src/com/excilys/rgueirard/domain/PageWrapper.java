package com.excilys.rgueirard.domain;

import java.util.ArrayList;
import java.util.List;

public class PageWrapper<E> {
	private int size;
	private int nbDisplay;
	private int nbPages;
	private int currentPage;
	private List<E> pages;
	private int orderBy;
	private boolean ascendant;
	private int searchType;
	private String searchMotif;

	public PageWrapper() {
		super();
		pages = new ArrayList<E>();
		size = 0;
		nbDisplay = 50;
		nbPages = 0;
		currentPage = 1;
		orderBy = 1;
		ascendant = true;
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

	public List<E> getPages() {
		return pages;
	}

	public void setPages(List<E> pages) {
		this.pages = pages;
	}
	
	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAscendant() {
		return ascendant;
	}

	public void setAscendant(boolean ascendant) {
		this.ascendant = ascendant;
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
		return "PageWrapper [size=" + size + ", nbDisplay=" + nbDisplay
				+ ", nbPages=" + nbPages + ", currentPage=" + currentPage
				+ ", pages=" + pages + ", orderBy=" + orderBy + ", ascendant="
				+ ascendant + ", searchType=" + searchType + ", searchMotif="
				+ searchMotif + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ascendant ? 1231 : 1237);
		result = prime * result + currentPage;
		result = prime * result + nbDisplay;
		result = prime * result + nbPages;
		result = prime * result + orderBy;
		result = prime * result + ((pages == null) ? 0 : pages.hashCode());
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
		PageWrapper<?> other = (PageWrapper<?>) obj;
		if (ascendant != other.ascendant)
			return false;
		if (currentPage != other.currentPage)
			return false;
		if (nbDisplay != other.nbDisplay)
			return false;
		if (nbPages != other.nbPages)
			return false;
		if (orderBy != other.orderBy)
			return false;
		if (pages == null) {
			if (other.pages != null)
				return false;
		} else if (!pages.equals(other.pages))
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

	public static class Builder<E> {
		PageWrapper<E> dashboardWrapper;

		public Builder() {
			dashboardWrapper = new PageWrapper<E>();
		}

		public Builder<E> size(int size) {
			this.dashboardWrapper.size = size;
			return this;
		}

		public Builder<E> nbDisplay(int nbDisplay) {
			this.dashboardWrapper.nbDisplay = nbDisplay;
			return this;
		}

		public Builder<E> nbPages(int nbPages) {
			this.dashboardWrapper.nbPages = nbPages;
			return this;
		}

		public Builder<E> currentPage(int currentPage) {
			this.dashboardWrapper.currentPage = currentPage;
			return this;
		}

		public Builder<E> pages(List<E> pages) {
			this.dashboardWrapper.pages = pages;
			return this;
		}
		
		public Builder<E> orderBy(int orderBy) {
			this.dashboardWrapper.orderBy = orderBy;
			return this;
		}
		
		public Builder<E> ascendant(boolean ascendant) {
			this.dashboardWrapper.ascendant = ascendant;
			return this;
		}
		
		public Builder<E> searchType(int searchType) {
			this.dashboardWrapper.searchType = searchType;
			return this;
		}
		
		public Builder<E> searchMotif(String searchMotif) {
			this.dashboardWrapper.searchMotif = searchMotif;
			return this;
		}

		public PageWrapper<E> build() {
			return this.dashboardWrapper;
		}
	}

	public static Builder<?> builder() {
		return new Builder<Object>();
	}

}
