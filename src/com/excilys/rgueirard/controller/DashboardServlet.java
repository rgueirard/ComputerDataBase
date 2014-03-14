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
import com.excilys.rgueirard.persistence.ComputerDAO;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Computer> computers = new ArrayList<Computer>();
			
		computers = ComputerDAO.retrieveAll(1);

		request.setAttribute("computers", computers);
		request.setAttribute("size", computers.size());
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
		int searchType = Integer.parseInt(request.getParameter("searchtype"));
		int orderBy = 1;
		
		if(request.getParameter("orderby")!= null){
			orderBy = Integer.parseInt(request.getParameter("orderby"));
		}
		
		List<Computer> computers = new ArrayList<Computer>();

		if (searchText.isEmpty()) {
			computers = ComputerDAO.retrieveAll(orderBy);
		} else {

			if (searchType == 0) {
				computers.add(ComputerDAO.retrieve(searchText, orderBy));
			} else {
				if (searchType == 1) {
					computers = ComputerDAO.retrieveByName(searchText, orderBy);
				} else {
					if (searchType == 2) {
						computers = ComputerDAO
								.retrieveByIntroduced(searchText, orderBy);
					} else {
						if (searchType == 3) {
							computers = ComputerDAO
									.retrieveByDiscontinued(searchText, orderBy);
						} else {
							if (searchType == 4) {
								computers = ComputerDAO
										.retrieveByCompany(searchText, orderBy);
							}
						}
					}
				}
			}
		}
		request.setAttribute("computers", computers);
		request.setAttribute("size", computers.size());

		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);

	}

}
