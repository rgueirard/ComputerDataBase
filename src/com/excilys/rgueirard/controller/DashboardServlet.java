package com.excilys.rgueirard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.domain.PageWrapper;
import com.excilys.rgueirard.service.ComputerService;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ComputerService computerService = ComputerService.getInstance();
		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();

		/* recupération de page */
		if ((request.getParameter("page") != null)
				&& (request.getParameter("page") != "")) {
			wrapper.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		}

		/* recuperation de nbCptValue et nbDisplay */
		if ((request.getParameter("nbDisplay") != null)
				&& (request.getParameter("nbDisplay") != "")) {
			wrapper.setNbDisplay(Integer.parseInt(request.getParameter("nbDisplay")));
		}

		/* recuperation de orderBy */
		if ((request.getParameter("orderBy") != null)
				&& (request.getParameter("orderBy") != "")) {
			wrapper.setOrderBy(Integer.parseInt(request.getParameter("orderBy")));
		}

		/* recuperation de searchType */
		if ((request.getParameter("searchType") != null)
				&& (request.getParameter("searchType") != "")) {
			wrapper.setSearchType(Integer.parseInt(request.getParameter("searchType")));
		}

		/* recuperation du motif recherche */
		if ((request.getParameter("searchMotif") != null)
				&& (request.getParameter("searchMotif") != "")) {
			wrapper.setSearchMotif(request.getParameter("searchMotif"));
		}

		/* recuperation de la liste d'ordinateur */
		if (wrapper.getSearchMotif().isEmpty()) {
			wrapper = computerService.retrieveAll(wrapper);
		} else {

			if (wrapper.getSearchType() == 0) {
				wrapper = computerService.retrieveByName(wrapper);
			} else {
				if (wrapper.getSearchType() == 1) {
					wrapper = computerService.retrieveByCompany(wrapper);
				} else {
					/*if (wrapper.getSearchType() == 2) {*/
						wrapper = computerService.retrieve(wrapper);
					/*} else {
						if (wrapper.getSearchType() == 3) {
							wrapper = computerService.retrieveByIntroduced(wrapper);
						} else {
							if (wrapper.getSearchType() == 4) {
								wrapper = computerService
										.retrieveByDiscontinued(wrapper);
							}
						}
					}*/
				}
			}
		}

		/* recuperation de nbPages */
		wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
				/ wrapper.getNbDisplay()));

		request.setAttribute("wrapper", wrapper);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ComputerService computerService = ComputerService.getInstance();
		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();

		/* recupération de page */
		if ((request.getParameter("page") != null)
				&& (request.getParameter("page") != "")) {
			wrapper.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		}

		/* recuperation de nbCptValue et nbDisplay */
		if ((request.getParameter("nbDisplay") != null)
				&& (request.getParameter("nbDisplay") != "")) {
			wrapper.setNbDisplay(Integer.parseInt(request.getParameter("nbDisplay")));
		}

		/* recuperation de orderBy */
		if ((request.getParameter("orderBy") != null)
				&& (request.getParameter("orderBy") != "")) {
			wrapper.setOrderBy(Integer.parseInt(request.getParameter("orderBy")));
		}

		/* recuperation de searchType */
		if ((request.getParameter("searchType") != null)
				&& (request.getParameter("searchType") != "")) {
			wrapper.setSearchType(Integer.parseInt(request.getParameter("searchType")));
		}

		/* recuperation du motif recherche */
		if ((request.getParameter("searchMotif") != null)
				&& (request.getParameter("searchMotif") != "")) {
			wrapper.setSearchMotif(request.getParameter("searchMotif"));
		}

		/* recuperation de la liste d'ordinateur */
		if (wrapper.getSearchMotif().isEmpty()) {
			wrapper = computerService.retrieveAll(wrapper);
		} else {

			if (wrapper.getSearchType() == 0) {
				wrapper = computerService.retrieveByName(wrapper);
			} else {
				if (wrapper.getSearchType() == 1) {
					wrapper = computerService.retrieveByCompany(wrapper);
				} else {
					/*if (wrapper.getSearchType() == 2) {*/
						wrapper = computerService.retrieve(wrapper);
					/*} else {
						if (wrapper.getSearchType() == 3) {
							wrapper = computerService.retrieveByIntroduced(wrapper);
						} else {
							if (wrapper.getSearchType() == 4) {
								wrapper = computerService
										.retrieveByDiscontinued(wrapper);
							}
						}
					}*/
				}
			}
		}

		/* recuperation de nbPages */
		wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
				/ wrapper.getNbDisplay()));

		request.setAttribute("wrapper", wrapper);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

}
