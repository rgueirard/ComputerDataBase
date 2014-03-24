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
import com.excilys.rgueirard.domain.ComputerDTO;
import com.excilys.rgueirard.domain.ErrorWrapper;
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
		
		if (request.getAttribute("computer") != null) {
			request.setAttribute("computer", request.getAttribute("computer"));
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
		
		ComputerService computerService = ComputerService.getInstance();
		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();
		ErrorWrapper error = new ErrorWrapper();
		ComputerDTO computerDTO = null;
		long companyId = 0;
		
		String name = request.getParameter("name");
		String introduced = request.getParameter("introducedDate");
		String discontinued = request.getParameter("discontinuedDate");

		if ((request.getParameter("company") != null)
				&& (request.getParameter("company") != "")) {
			companyId = Long.parseLong(request.getParameter("company"));
		} request.getParameter("company");
		
		computerDTO = ComputerDTO.builder().name(name)
				.introduced(introduced).discontinued(discontinued)
				.companyId(companyId).build();
		
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

		error = ComputerValidator.validate(computerDTO);
		if (error.isState()) {
			request.setAttribute("error", error);
			request.setAttribute("computer", computerDTO);
			doGet(request, response);
		} else {
			computerService.create(computerDTO);
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

}
