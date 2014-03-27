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
import com.excilys.rgueirard.dto.ComputerDTO;
import com.excilys.rgueirard.mapper.ComputerMapper;
import com.excilys.rgueirard.mapper.WrapperMapper;
import com.excilys.rgueirard.service.CompanyService;
import com.excilys.rgueirard.service.ComputerService;
import com.excilys.rgueirard.validator.ComputerValidator;
import com.excilys.rgueirard.wrapper.ErrorWrapper;
import com.excilys.rgueirard.wrapper.PageWrapper;

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
		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();
		PageWrapper<ComputerDTO> wrapperDTO = new PageWrapper<ComputerDTO>();
		ComputerDTO computerDTO = null;
		Computer computer = null;
		// String searchMotif = "";
		// int searchType = 0;

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

		/* recuperation de l'ancien motif de recherche */
		if ((request.getParameter("searchMotif") != null)
				&& (request.getParameter("searchMotif") != "")) {
			wrapper.setSearchMotif((request.getParameter("searchMotif")));
		}

		List<Company> companies = companyService.retrieveAll();

		wrapperDTO = WrapperMapper.computerToDTO(wrapper);

		if (request.getAttribute("computer") != null) {
			request.setAttribute("computer", request.getAttribute("computer"));

		} else {
			computer = computerService.retrieveById(Long.parseLong(request
					.getParameter("id")));
			computerDTO = ComputerMapper.computerToDTO(computer);
			request.setAttribute("computer", computerDTO);
		}

		request.setAttribute("companies", companies);
		request.setAttribute("wrapper", wrapperDTO);
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
		Computer computer = null;
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
			computer = ComputerMapper.DTOToComputer(computerDTO);
			computerService.update(computer);
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
