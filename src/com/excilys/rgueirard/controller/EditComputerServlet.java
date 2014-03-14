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
import com.excilys.rgueirard.persistence.CompanyDAO;
import com.excilys.rgueirard.persistence.ComputerDAO;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Computer computer =	ComputerDAO.retrieve(id, 1);
		List<Company> companies  = CompanyDAO.retrieveAll();
		request.setAttribute("computer", computer);
		request.setAttribute("companies", companies);
		this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("company");
		List<Computer> computers = new ArrayList<Computer>();
		
		ComputerDAO.update(id, name, introduced, discontinued, companyId);
		
		computers = ComputerDAO.retrieveAll(1);
		
		request.setAttribute("computers", computers);
		request.setAttribute("size", computers.size());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
		.forward(request, response);
	}

}
