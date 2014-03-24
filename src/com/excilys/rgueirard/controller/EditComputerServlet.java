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
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ComputerService computerService = ComputerService.getInstance();
		CompanyService companyService = CompanyService.getInstance();

		PageWrapper<ComputerDTO> wrapper = new PageWrapper<ComputerDTO>();
		String searchMotif = "";
		int searchType = 0;

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
			searchType = Integer.parseInt(request.getParameter("searchType"));
		}

		/* recuperation de l'ancien motif de recherche */
		if ((request.getParameter("searchMotif") != null)
				&& (request.getParameter("searchMotif") != "")) {
			searchMotif = (request.getParameter("searchMotif"));
		}

		if (request.getParameter("computer") != null) {

		}

		/* recuperation du motif recherche */
		wrapper.setSearchMotif(request.getParameter("id"));
		wrapper.setSearchType(2);
		wrapper = computerService.retrieve(wrapper);
		List<Company> companies = companyService.retrieveAll();

		wrapper.setSearchType(searchType);
		wrapper.setSearchMotif(searchMotif);
		
		if (request.getAttribute("computer") != null) {
			request.setAttribute("computer", request.getAttribute("computer"));

		} else {
			request.setAttribute("computer", wrapper.getPages().get(0));
		}
		
		request.setAttribute("companies", companies);
		request.setAttribute("wrapper", wrapper);
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/editComputer.jsp")
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
		ComputerDTO computerDTO = null;
		long id = 0;
		long companyId = 0;
		ErrorWrapper error = new ErrorWrapper();

		if ((request.getParameter("id") != null)
				&& (request.getParameter("id") != "")) {
			id = Long.parseLong(request.getParameter("id"));

		}
		String name = request.getParameter("name");
		String introduced = request.getParameter("introducedDate");
		String discontinued = request.getParameter("discontinuedDate");

		if ((request.getParameter("company") != null)
				&& (request.getParameter("company") != "")) {
			companyId = Long.parseLong(request.getParameter("company"));
		}

		computerDTO = ComputerDTO.builder().id(id).name(name)
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
			computerService.update(computerDTO);
			this.getServletContext()
					.getRequestDispatcher(
							"/dashboard?page=" + wrapper.getCurrentPage()
									+ "&nbDisplay=" + wrapper.getNbDisplay()
									+ "&orderBy=" + wrapper.getOrderBy()
									+ "&searchType=" + wrapper.getSearchType()
									+ "&searchMotif="
									+ wrapper.getSearchMotif())
					.forward(request, response);
		}

	}
}
