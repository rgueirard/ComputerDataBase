package com.excilys.rgueirard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.rgueirard.service.ComputerService;

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
	
	@Autowired
	private ComputerService computerService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
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
		if ((request.getParameter("orderBy") != null)&&(request.getParameter("orderBy") != "")) {
			orderBy = Integer.parseInt(request.getParameter("orderBy"));
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
	@RequestMapping(method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
