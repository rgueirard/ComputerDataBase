package com.excilys.rgueirard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;
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
		String id = request.getParameter("id");
		Computer computer = computerService.retrieve(id, 1);
		CompanyService companyService = CompanyService.getInstance();
		List<Company> companies = companyService.retrieveAll();
		request.setAttribute("computer", computer);
		request.setAttribute("companies", companies);
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
		List<Computer> computers = new ArrayList<Computer>();
		ComputerService computerService = ComputerService.getInstance();
		computerService.update(id, name, introduced, discontinued, companyId);

		int page = 1;
		int nbDisplay = 50;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int nbComputers = computerService.count();
		int nbPages = (int) Math.ceil(nbComputers * 1.0 / nbDisplay);

		computers = computerService.retrieveAll(1, (page - 1) * nbDisplay,
				nbDisplay);

		request.setAttribute("computers", computers);
		request.setAttribute("size", nbComputers);
		request.setAttribute("nbPages", nbPages);
		request.setAttribute("currentPage", page);

		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

}
