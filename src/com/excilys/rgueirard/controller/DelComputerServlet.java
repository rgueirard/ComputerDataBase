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
 * Servlet implementation class DelComputerServlet
 */
@WebServlet("/DelComputerServlet")
public class DelComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ComputerService computerService = ComputerService.getInstance();
		computerService.delete(id);
		int orderBy = 1;
		if (request.getParameter("orderby") != null) {
			Integer.parseInt(request.getParameter("orderby"));
		}

		int page = 1;
		int nbDisplay = 50;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int nbComputers = computerService.count();
		int nbPages = (int) Math.ceil(nbComputers * 1.0 / nbDisplay);

		List<Computer> computers = new ArrayList<Computer>();
		computers = computerService.retrieveAll(orderBy,
				(page - 1) * nbDisplay, nbDisplay);

		request.setAttribute("computers", computers);
		request.setAttribute("size", nbComputers);
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
		// TODO Auto-generated method stub
	}

}
