package com.excilys.rgueirard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.ComputerWrapper;
import com.excilys.rgueirard.persistence.CompanyService;
import com.excilys.rgueirard.persistence.ComputerService;

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

		int page = 1;
		if ((request.getParameter("page") != null)&&(request.getParameter("page") != "")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int nbCptValue = 1;
		if ((request.getParameter("nbByPage") != null)
				&& (request.getParameter("nbByPage") != "")) {
			nbCptValue = Integer.parseInt(request.getParameter("nbByPage"));
		}
		int orderBy = 1;
		if ((request.getParameter("orderby") != null)&&(request.getParameter("orderby") != "")) {
			orderBy = Integer.parseInt(request.getParameter("orderby"));
		}
		int searchType = 0;
		if ((request.getParameter("searchType") != null)&&(request.getParameter("searchType") != "")) {
			searchType = Integer.parseInt(request.getParameter("searchType"));
		}
		String searchMotif = "";
		if ((request.getParameter("searchMotif") != null)&&(request.getParameter("searchMotif") != "")) {
			searchMotif = request.getParameter("searchMotif");
		}

		String id = request.getParameter("id");

		ComputerWrapper cptWrapper = computerService.retrieve(id, 1, 0, 1);
		List<Company> companies = companyService.retrieveAll();

		request.setAttribute("computer", cptWrapper.getComputer());
		request.setAttribute("companies", companies);
		request.setAttribute("currentPage", page);
		request.setAttribute("nbDisplay", nbCptValue);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchMotif", searchMotif);
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

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("company");

		ComputerService computerService = ComputerService.getInstance();

		computerService.update(id, name, introduced, discontinued, companyId);

		int page = 1;
		if ((request.getParameter("page") != null)&&(request.getParameter("page") != "")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int nbCptValue = 1;
		if ((request.getParameter("nbByPage") != null)
				&& (request.getParameter("nbByPage") != "")) {
			nbCptValue = Integer.parseInt(request.getParameter("nbByPage"));
		}
		int orderBy = 1;
		if ((request.getParameter("orderby") != null)&&(request.getParameter("orderby") != "")) {
			orderBy = Integer.parseInt(request.getParameter("orderby"));
		}
		int searchType = 0;
		if ((request.getParameter("searchType") != null)&&(request.getParameter("searchType") != "")) {
			searchType = Integer.parseInt(request.getParameter("searchType"));
		}
		String searchMotif = "";
		if ((request.getParameter("searchMotif") != null)&&(request.getParameter("searchMotif") != "")) {
			searchMotif = request.getParameter("searchMotif");
		}

		this.getServletContext()
				.getRequestDispatcher(
						"/dashboard?page=" + page + "&nbByPage=" + nbCptValue
								+ "&orderBy=" + orderBy + "&searchType="
								+ searchType + "&searchMotif=" + searchMotif)
				.forward(request, response);
	}

}
