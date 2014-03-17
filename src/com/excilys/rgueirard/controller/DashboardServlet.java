package com.excilys.rgueirard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.persistence.ComputerService;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int orderBy;
	private static int searchType;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int nbDisplay = 50;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		List<Computer> computers = new ArrayList<Computer>();
		ComputerService computerService = ComputerService.getInstance();
		computers = computerService.retrieveAll(1, (page - 1) * nbDisplay,
				nbDisplay);

		int nbComputers = computerService.count();
		int nbPages = (int) Math.ceil(nbComputers * 1.0 / nbDisplay);
		
		request.setAttribute("computers", computers);
		request.setAttribute("size", nbComputers);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("searchType", searchType);
		request.setAttribute("nbPages", nbPages);
		request.setAttribute("currentPage", page);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchText = request.getParameter("search");
		searchType = Integer.parseInt(request.getParameter("searchtype"));
		orderBy = 1;
		int page = 1;
		int nbDisplay = 15;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		List<Computer> computers = new ArrayList<Computer>();
		ComputerService computerService = ComputerService.getInstance();

		int nbComputers = computerService.count();
		int nbPages = (int) Math.ceil(nbComputers * 1.0 / nbDisplay);
		
		
		if (request.getParameter("orderby") != null) {
			orderBy = Integer.parseInt(request.getParameter("orderby"));
		}

		if (searchText.isEmpty()) {
			computers = computerService.retrieveAll(orderBy, (page - 1)
					* nbDisplay, nbDisplay);
		} else {

			if (searchType == 0) {
				computers.add(computerService.retrieve(searchText, orderBy));
			} else {
				if (searchType == 1) {
					computers = computerService.retrieveByName(searchText,
							orderBy);
				} else {
					if (searchType == 2) {
						computers = computerService.retrieveByIntroduced(
								searchText, orderBy);
					} else {
						if (searchType == 3) {
							computers = computerService.retrieveByDiscontinued(
									searchText, orderBy);
						} else {
							if (searchType == 4) {
								computers = computerService.retrieveByCompany(
										searchText, orderBy);
							}
						}
					}
				}
			}
		}

		request.setAttribute("computers", computers);
		request.setAttribute("size", nbComputers);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("searchType", searchType);
		request.setAttribute("nbPages", nbPages);
		request.setAttribute("currentPage", page);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);

	}

}
