package com.excilys.rgueirard.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.persistence.ComputerDAO;

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

		name = request.getParameter("name");
		introducedDate = request.getParameter("introducedDate");
		discontinuedDate = request.getParameter("discontinuedDate");
		company = request.getParameter("company");

		ComputerDAO.create(name, introducedDate, discontinuedDate, company);
		
		List<Computer> computers = new ArrayList<Computer>();
		computers = ComputerDAO.retrieveAll();
		
		request.setAttribute("computers", computers);
		request.setAttribute("size", computers.size());
		this.getServletContext().getRequestDispatcher("/dashboard.jsp")
				.forward(request, response);
	}

}
