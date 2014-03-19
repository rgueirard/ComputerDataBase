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
import com.excilys.rgueirard.domain.DashboardWrapper;
import com.excilys.rgueirard.persistence.ComputerService;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
		List<Computer> computers = new ArrayList<Computer>();
		ComputerService computerService = ComputerService.getInstance();
		int nbComputers;
		int nbPages;
		
		int page = 1;
		int nbCptValue = 1;
		if ((request.getParameter("page") != null)&&(request.getParameter("page") != "")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int nbDisplay = 50;
		if ((request.getParameter("nbByPage") != null)
				&& (request.getParameter("nbByPage") != "")) {
			nbCptValue = Integer.parseInt(request.getParameter("nbByPage"));
			if (nbCptValue == 0) {
				nbDisplay = 25;
			} else {
				if (nbCptValue == 2) {
					nbDisplay = 75;
				} else {
					if (nbCptValue == 3) {
						nbDisplay = 100;
					}
				}
			}
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
		
		/*	recuperation de la liste d'ordinateur	*/
		if (searchMotif.isEmpty()) {
			computers = computerService.retrieveAll(orderBy, (page - 1)
					* nbDisplay, nbDisplay);
		} else {

			if (searchType == 0) {
				computers = computerService.retrieveByName(searchMotif, orderBy);
			} else {
				if (searchType == 1) {
					computers = computerService.retrieveByCompany(searchMotif,
							orderBy);
				} else {
					if (searchType == 2) {
						computers.add(computerService.retrieve(searchMotif,
								orderBy));
					} else {
						if (searchType == 3) {
							computers = computerService.retrieveByIntroduced(
									searchMotif, orderBy);
						} else {
							if (searchType == 4) {
								computers = computerService
										.retrieveByDiscontinued(searchMotif,
												orderBy);
							}
						}
					}
				}
			}
		}
		
		/*	recuperation du nombre de pc dans la base	ps: a changer*/
		nbComputers = computerService.count();
		
		/*	recuperation de nbPages	*/
		nbPages = (int) Math.ceil(nbComputers * 1.0 / nbDisplay);
		
	
		DashboardWrapper wrapper = DashboardWrapper.builder().size(nbComputers)
				.nbDisplay(nbCptValue).nbPages(nbPages).currentPage(page)
				.computers(computers).orderBy(orderBy).searchType(searchType).searchMotif(searchMotif)
				.build();
		
		request.setAttribute("wrapper", wrapper);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String searchMotif = "";		
		int page = 1;
		int nbCptValue = 1;
		int nbDisplay = 50;
		int orderBy = 1;
		int searchType = 0;
		List<Computer> computers = new ArrayList<Computer>();
		ComputerService computerService = ComputerService.getInstance();
		int nbComputers;
		int nbPages;
		
		/*	recupération de page	*/
		if ((request.getParameter("page") != null)&&(request.getParameter("page") != "")) {
			page = Integer.parseInt(request.getParameter("page"));
		}	
		
		/*	recuperation de nbCptValue et nbDisplay	*/
		if ((request.getParameter("nbByPage") != null)
				&& (request.getParameter("nbByPage") != "")) {
			nbCptValue = Integer.parseInt(request.getParameter("nbByPage"));
			if (nbCptValue == 0) {
				nbDisplay = 25;
			} else {
				if (nbCptValue == 2) {
					nbDisplay = 75;
				} else {
					if (nbCptValue == 3) {
						nbDisplay = 100;
					}
				}
			}
		}
		
		/*	recuperation de orderBy	*/
		if ((request.getParameter("orderby") != null)&&(request.getParameter("orderby") != "")) {
			orderBy = Integer.parseInt(request.getParameter("orderBy"));
		}
		
		/*	recuperation de searchType	*/
		if ((request.getParameter("searchType") != null)&&(request.getParameter("searchType") != "")) {
			searchType = Integer.parseInt(request.getParameter("searchType"));
		}
		
		/*	recuperation du motif recherche	*/
		if ((request.getParameter("searchMotif") != null)&&(request.getParameter("searchMotif") != "")) {
			 searchMotif = request.getParameter("searchMotif");
		}
		
		/*	recuperation de la liste d'ordinateur	*/
		if (searchMotif.isEmpty()) {
			computers = computerService.retrieveAll(orderBy, (page - 1)
					* nbDisplay, nbDisplay);
		} else {

			if (searchType == 0) {
				computers = computerService.retrieveByName(searchMotif, orderBy);
			} else {
				if (searchType == 1) {
					computers = computerService.retrieveByCompany(searchMotif,
							orderBy);
				} else {
					if (searchType == 2) {
						computers.add(computerService.retrieve(searchMotif,
								orderBy));
					} else {
						if (searchType == 3) {
							computers = computerService.retrieveByIntroduced(
									searchMotif, orderBy);
						} else {
							if (searchType == 4) {
								computers = computerService
										.retrieveByDiscontinued(searchMotif,
												orderBy);
							}
						}
					}
				}
			}
		}
		
		/*	recuperation du nombre de pc dans la base	ps: a changer*/
		nbComputers = computerService.count();
		
		/*	recuperation de nbPages	*/
		nbPages = (int) Math.ceil(nbComputers * 1.0 / nbDisplay);

		/* création de l'objet a envoyer */
		DashboardWrapper wrapper = DashboardWrapper.builder().size(nbComputers)
				.nbDisplay(nbCptValue).nbPages(nbPages).currentPage(page)
				.computers(computers).orderBy(orderBy).searchType(searchType).searchMotif(searchMotif)
				.build();
		
		request.setAttribute("wrapper", wrapper);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

}
