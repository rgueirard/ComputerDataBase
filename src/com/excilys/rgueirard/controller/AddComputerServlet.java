package com.excilys.rgueirard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.domain.PageWrapper;
import com.excilys.rgueirard.service.CompanyService;
import com.excilys.rgueirard.service.ComputerService;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();

		/* recupération de page */
		if ((request.getParameter("page") != null)
				&& (request.getParameter("page") != "")) {
			wrapper.setCurrentPage(Integer.parseInt(request
					.getParameter("page")));
		}

		/* recuperation de nbCptValue et nbDisplay */
		if ((request.getParameter("nbDisplay") != null)
				&& (request.getParameter("nbDisplay") != "")) {
			wrapper.setNbDisplay(Integer.parseInt(request
					.getParameter("nbDisplay")));
		}

		/* recuperation de orderBy */
		if ((request.getParameter("orderBy") != null)
				&& (request.getParameter("orderBy") != "")) {
			wrapper.setOrderBy(Integer.parseInt(request.getParameter("orderBy")));
		}

		/* recuperation de searchType */
		if ((request.getParameter("searchType") != null)
				&& (request.getParameter("searchType") != "")) {
			wrapper.setSearchType(Integer.parseInt(request
					.getParameter("searchType")));
		}

		/* recuperation du motif recherche */
		if ((request.getParameter("searchMotif") != null)
				&& (request.getParameter("searchMotif") != "")) {
			wrapper.setSearchMotif(request.getParameter("searchMotif"));
		}

		CompanyService companyService = CompanyService.getInstance();
		List<Company> companies = companyService.retrieveAll();

		request.setAttribute("companies", companies);
		request.setAttribute("wrapper", wrapper);
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/addComputer.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = "";
		String introducedDate = "";
		String discontinuedDate = "";
		String company = "";
		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();

		/* recupération de page */
		if ((request.getParameter("page") != null)
				&& (request.getParameter("page") != "")) {
			wrapper.setCurrentPage(Integer.parseInt(request
					.getParameter("page")));
		}

		/* recuperation de nbCptValue et nbDisplay */
		if ((request.getParameter("nbDisplay") != null)
				&& (request.getParameter("nbDisplay") != "")) {
			wrapper.setNbDisplay(Integer.parseInt(request
					.getParameter("nbDisplay")));
		}

		/* recuperation de orderBy */
		if ((request.getParameter("orderBy") != null)
				&& (request.getParameter("orderBy") != "")) {
			wrapper.setOrderBy(Integer.parseInt(request.getParameter("orderBy")));
		}

		/* recuperation de searchType */
		if ((request.getParameter("searchType") != null)
				&& (request.getParameter("searchType") != "")) {
			wrapper.setSearchType(Integer.parseInt(request
					.getParameter("searchType")));
		}

		/* recuperation du motif recherche */
		if ((request.getParameter("searchMotif") != null)
				&& (request.getParameter("searchMotif") != "")) {
			wrapper.setSearchMotif(request.getParameter("searchMotif"));
		}

		name = request.getParameter("name");
		introducedDate = request.getParameter("introducedDate");
		discontinuedDate = request.getParameter("discontinuedDate");
		company = request.getParameter("company");

		ComputerService computerService = ComputerService.getInstance();
		computerService.create(name, introducedDate, discontinuedDate, company);

		this.getServletContext()
				.getRequestDispatcher(
						"/dashboard?page=" + wrapper.getCurrentPage()
								+ "&nbDisplay=" + wrapper.getNbDisplay()
								+ "&orderBy=" + wrapper.getOrderBy()
								+ "&searchType=" + wrapper.getSearchType()
								+ "&searchMotif=" + wrapper.getSearchMotif())
				.forward(request, response);
	}

}
