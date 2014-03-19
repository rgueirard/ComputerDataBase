package com.excilys.rgueirard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
