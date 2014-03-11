package com.excilys.rgueirard.servlets;

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

import com.excilys.rgueirard.beans.Computer;
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

		/* verif des parametre (a faire) */

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		Date introduced = null;
		Date discontinued = null;
		try {
			introduced = formatter.parse(introducedDate);
			discontinued = formatter.parse(discontinuedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Computer computer = new Computer(name, introduced, discontinued,
				Integer.parseInt(company));

		/*
		 * addDataBase(name, introducedDate, discontinuedDate, company); ajout
		 * en base
		 */

		List<Computer> computers = new ArrayList<Computer>();
		computers = ComputerDAO.getAllComputers();
		/* triche pour test computers.add(computer); */
		request.setAttribute("computers", computers);

		this.getServletContext().getRequestDispatcher("/dashboard.jsp")
				.forward(request, response);
	}

}
