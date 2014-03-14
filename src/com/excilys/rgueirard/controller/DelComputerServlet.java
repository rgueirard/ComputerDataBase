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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ComputerDAO.delete(id);
		int orderBy = 1;
		if(request.getParameter("orderby")!=null){
			Integer.parseInt(request.getParameter("orderby"));
		}
		List<Computer> computers = new ArrayList<Computer>();
		computers = ComputerDAO.retrieveAll(orderBy);
		
		request.setAttribute("computers", computers);
		request.setAttribute("size", computers.size());
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
