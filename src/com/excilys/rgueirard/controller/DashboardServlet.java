package com.excilys.rgueirard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.rgueirard.domain.ComputerWrapper;
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

		ComputerWrapper cptWrapper = null;
		ComputerService computerService = ComputerService.getInstance();
		int nbPages;

		int page = 1;
		int nbCptValue = 1;
		if ((request.getParameter("page") != null)
				&& (request.getParameter("page") != "")) {
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
		if ((request.getParameter("orderby") != null)
				&& (request.getParameter("orderby") != "")) {
			orderBy = Integer.parseInt(request.getParameter("orderby"));
		}
		int searchType = 0;
		if ((request.getParameter("searchType") != null)
				&& (request.getParameter("searchType") != "")) {
			searchType = Integer.parseInt(request.getParameter("searchType"));
		}
		String searchMotif = "";
		if ((request.getParameter("searchMotif") != null)
				&& (request.getParameter("searchMotif") != "")) {
			searchMotif = request.getParameter("searchMotif");
		}

		/* recuperation de la liste d'ordinateur */
		if (searchMotif.isEmpty()) {
			cptWrapper = computerService.retrieveAll(orderBy, (page - 1)
					* nbDisplay, nbDisplay);
		} else {

			if (searchType == 0) {
				cptWrapper = computerService.retrieveByName(searchMotif,
						orderBy, (page - 1) * nbDisplay, nbDisplay);
			} else {
				if (searchType == 1) {
					cptWrapper = computerService.retrieveByCompany(searchMotif,
							orderBy, (page - 1) * nbDisplay, nbDisplay);
				} else {
					if (searchType == 2) {
						cptWrapper = computerService.retrieve(searchMotif,
								orderBy, (page - 1) * nbDisplay, nbDisplay);
					} else {
						if (searchType == 3) {
							cptWrapper = computerService.retrieveByIntroduced(
									searchMotif, orderBy, (page - 1)
											* nbDisplay, nbDisplay);
						} else {
							if (searchType == 4) {
								cptWrapper = computerService
										.retrieveByDiscontinued(searchMotif,
												orderBy,
												(page - 1) * nbDisplay,
												nbDisplay);
							}
						}
					}
				}
			}
		}

		nbPages = (int) Math.ceil(cptWrapper.getSize() * 1.0 / nbDisplay);

		DashboardWrapper wrapper = DashboardWrapper.builder()
				.size(cptWrapper.getSize()).nbDisplay(nbCptValue)
				.nbPages(nbPages).currentPage(page)
				.computers(cptWrapper.getComputers()).orderBy(orderBy)
				.searchType(searchType).searchMotif(searchMotif).build();

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
		ComputerWrapper cptWrapper = null;
		ComputerService computerService = ComputerService.getInstance();
		int nbPages;

		/* recupération de page */
		if ((request.getParameter("page") != null)
				&& (request.getParameter("page") != "")) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		/* recuperation de nbCptValue et nbDisplay */
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

		/* recuperation de orderBy */
		if ((request.getParameter("orderby") != null)
				&& (request.getParameter("orderby") != "")) {
			orderBy = Integer.parseInt(request.getParameter("orderBy"));
		}

		/* recuperation de searchType */
		if ((request.getParameter("searchType") != null)
				&& (request.getParameter("searchType") != "")) {
			searchType = Integer.parseInt(request.getParameter("searchType"));
		}

		/* recuperation du motif recherche */
		if ((request.getParameter("searchMotif") != null)
				&& (request.getParameter("searchMotif") != "")) {
			searchMotif = request.getParameter("searchMotif");
		}

		/* recuperation de la liste d'ordinateur */
		if (searchMotif.isEmpty()) {
			cptWrapper = computerService.retrieveAll(orderBy, (page - 1)
					* nbDisplay, nbDisplay);
		} else {

			if (searchType == 0) {
				cptWrapper = computerService.retrieveByName(searchMotif,
						orderBy, (page - 1) * nbDisplay, nbDisplay);
			} else {
				if (searchType == 1) {
					cptWrapper = computerService.retrieveByCompany(searchMotif,
							orderBy, (page - 1) * nbDisplay, nbDisplay);
				} else {
					if (searchType == 2) {
						cptWrapper = computerService.retrieve(searchMotif,
								orderBy, (page - 1) * nbDisplay, nbDisplay);
					} else {
						if (searchType == 3) {
							cptWrapper = computerService.retrieveByIntroduced(
									searchMotif, orderBy, (page - 1)
											* nbDisplay, nbDisplay);
						} else {
							if (searchType == 4) {
								cptWrapper = computerService
										.retrieveByDiscontinued(searchMotif,
												orderBy,
												(page - 1) * nbDisplay,
												nbDisplay);
							}
						}
					}
				}
			}
		}

		/* recuperation de nbPages */
		nbPages = (int) Math.ceil(cptWrapper.getSize() * 1.0 / nbDisplay);

		/* création de l'objet a envoyer */
		DashboardWrapper wrapper = DashboardWrapper.builder().size(cptWrapper.getSize())
				.nbDisplay(nbCptValue).nbPages(nbPages).currentPage(page)
				.computers(cptWrapper.getComputers()).orderBy(orderBy).searchType(searchType)
				.searchMotif(searchMotif).build();

		request.setAttribute("wrapper", wrapper);
		this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

}
