package com.excilys.rgueirard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.dto.ComputerDTO;
import com.excilys.rgueirard.mapper.ComputerMapper;
import com.excilys.rgueirard.mapper.WrapperMapper;
import com.excilys.rgueirard.service.CompanyService;
import com.excilys.rgueirard.service.ComputerService;
import com.excilys.rgueirard.validator.ComputerValidator;
import com.excilys.rgueirard.wrapper.ErrorWrapper;
import com.excilys.rgueirard.wrapper.PageWrapper;

@Controller
public class ComputerController {

	private static final String PARAM_PAGE = "page";
	private static final String PARAM_NB_DISPLAY = "nbDisplay";
	private static final String PARAM_ORDER_BY = "orderBy";
	private static final String PARAM_ASCENDANT = "ascendant";
	private static final String PARAM_SEARCH_TYPE = "searchType";
	private static final String PARAM_SEARCH_MOTIF = "searchMotif";
	private static final String PARAM_ID = "id";
	private static final String PARAM_EDIT = "edit";
	private static final String PARAM_NAME = "name";
	private static final String PARAM_INTRODUCED = "introducedDate";
	private static final String PARAM_DISCONTINUED = "discontinuedDate";
	private static final String PARAM_COMPANY_ID = "company";

	private static Logger logger = LoggerFactory
			.getLogger(ComputerController.class);

	@Autowired
	private ComputerService computerService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerMapper computerMapper;

	@Autowired
	private WrapperMapper wrapperMapper;

	@Autowired
	private ComputerValidator computerValidator;

	private PageWrapper<Computer> getAttr(int page, int nbDisplay, int orderBy,
			boolean ascendant, int searchType, String searchMotif) {

		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();

		/* recupération de page */
		if (page > 0) {
			wrapper.setCurrentPage(page);
		}

		/* recuperation de nbCptValue et nbDisplay */
		wrapper.setNbDisplay(nbDisplay);

		/* recuperation de orderBy */
		wrapper.setOrderBy(orderBy);

		/* recuperation de ascendant */
		wrapper.setAscendant(ascendant);

		/* recuperation de searchType */
		wrapper.setSearchType(searchType);

		/* recuperation du motif recherche */
		if ((searchMotif != null) && (searchMotif != "")) {
			wrapper.setSearchMotif(searchMotif);
		}
		return wrapper;
	}

	@RequestMapping(value = "/computer/add", method = RequestMethod.GET)
	public String add(
			ModelMap model,
			@RequestParam(value = PARAM_PAGE, required = false, defaultValue = "1") int page,
			@RequestParam(value = PARAM_NB_DISPLAY, required = false, defaultValue = "50") int nbDisplay,
			@RequestParam(value = PARAM_ORDER_BY, required = false, defaultValue = "1") int orderBy,
			@RequestParam(value = PARAM_ASCENDANT, required = false, defaultValue = "true") boolean ascendant,
			@RequestParam(value = PARAM_SEARCH_TYPE, required = false, defaultValue = "0") int searchType,
			@RequestParam(value = PARAM_SEARCH_MOTIF, required = false) String searchMotif,
			@RequestParam(value = PARAM_ID, required = false, defaultValue = "0") long id,
			@RequestParam(value = PARAM_EDIT, required = false) boolean edit) {

		logger.debug("entering addComputer");

		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();
		PageWrapper<ComputerDTO> wrapperDTO = new PageWrapper<ComputerDTO>();
		ComputerDTO computerDTO = null;
		Computer computer = null;

		wrapper = getAttr(page, nbDisplay, orderBy, ascendant, searchType,
				searchMotif);

		wrapperDTO = wrapperMapper.computerToDTO(wrapper);
		List<Company> companies = companyService.retrieveAll();

		model.addAttribute("companies", companies);
		model.addAttribute("wrapper", wrapperDTO);

		if (model.get("computer") != null) {
			model.addAttribute("computer", model.get("computer"));
		} else {
			if (edit) {
				computer = computerService.retrieveById(id);
				computerDTO = computerMapper.computerToDTO(computer);
				model.addAttribute("computer", computerDTO);

				return "editComputer";
			}

		}
		return "addComputer";
	}

	@RequestMapping(value = "/computer/submit", method = RequestMethod.GET)
	public String submit(
			ModelMap model,
			@RequestParam(value = PARAM_PAGE, required = false, defaultValue = "1") int page,
			@RequestParam(value = PARAM_NB_DISPLAY, required = false, defaultValue = "50") int nbDisplay,
			@RequestParam(value = PARAM_ORDER_BY, required = false, defaultValue = "1") int orderBy,
			@RequestParam(value = PARAM_ASCENDANT, required = false, defaultValue = "true") boolean ascendant,
			@RequestParam(value = PARAM_SEARCH_TYPE, required = false, defaultValue = "0") int searchType,
			@RequestParam(value = PARAM_SEARCH_MOTIF, required = false) String searchMotif,
			@RequestParam(value = PARAM_ID, required = false, defaultValue = "0") long id,
			@RequestParam(value = PARAM_EDIT, required = false) boolean edit,
			@RequestParam(value = PARAM_NAME, required = false) String name,
			@RequestParam(value = PARAM_INTRODUCED, required = false) String introduced,
			@RequestParam(value = PARAM_DISCONTINUED, required = false) String discontinued,
			@RequestParam(value = PARAM_COMPANY_ID, required = false, defaultValue = "0") long company) {

		StringBuilder sb = new StringBuilder("redirect:/computer/");
		ErrorWrapper error = new ErrorWrapper();
		ComputerDTO computerDTO = new ComputerDTO();
		Computer computer = null;

		computerDTO.setName(name);
		computerDTO.setIntroduced(introduced);
		computerDTO.setDiscontinued(discontinued);
		if (id != 0) {
			computerDTO.setId(id);
		}
		if (company != 0) {
			computerDTO.setCompanyId(company);
		}

		error = computerValidator.validate(computerDTO);
		if (error.isState()) {
			model.addAttribute("computer", computerDTO);
			model.addAttribute("error", error);
			
			sb.append("add?page=");
			sb.append(page);
			sb.append("&nbDisplay=");
			sb.append(nbDisplay);
			sb.append("&orderBy=");
			sb.append(orderBy);
			sb.append("&searchType=");
			sb.append(searchType);
			sb.append("&searchMotif=");
			sb.append(searchMotif);
			sb.append("&edit=");
			sb.append(edit);

		} else {
			computer = computerMapper.DTOToComputer(computerDTO);
			if (edit) {
				computerService.update(computer);
			} else {
				computerService.create(computer);
			}

			sb.append("show?page=");
			sb.append(page);
			sb.append("&nbDisplay=");
			sb.append(nbDisplay);
			sb.append("&orderBy=");
			sb.append(orderBy);
			sb.append("&searchType=");
			sb.append(searchType);
			sb.append("&searchMotif=");
			sb.append(searchMotif);
			sb.append("&edit=");
			sb.append(edit);
			
		}
		
		return sb.toString();
	}

	@RequestMapping(value = "/computer/del", method = RequestMethod.GET)
	public String delete(
			@RequestParam(value = PARAM_PAGE, required = false, defaultValue = "1") int page,
			@RequestParam(value = PARAM_NB_DISPLAY, required = false, defaultValue = "50") int nbDisplay,
			@RequestParam(value = PARAM_ORDER_BY, required = false, defaultValue = "1") int orderBy,
			@RequestParam(value = PARAM_ASCENDANT, required = false, defaultValue = "true") boolean ascendant,
			@RequestParam(value = PARAM_SEARCH_TYPE, required = false, defaultValue = "0") int searchType,
			@RequestParam(value = PARAM_SEARCH_MOTIF, required = false) String searchMotif,
			@RequestParam(value = PARAM_ID, required = false, defaultValue = "0") long id,
			ModelMap model) {

		StringBuilder sb = new StringBuilder("redirect:/computer/");
		if (id != 0) {
			computerService.delete(id);
		}

		sb.append("show?page=");
		sb.append(page);
		sb.append("&nbDisplay=");
		sb.append(nbDisplay);
		sb.append("&orderBy=");
		sb.append(orderBy);
		sb.append("&searchType=");
		sb.append(searchType);
		sb.append("&searchMotif=");
		sb.append(searchMotif);
				
		return sb.toString();
	}

	@RequestMapping(value = "/computer/show", method = RequestMethod.GET)
	public String show(
			ModelMap model,
			@RequestParam(value = PARAM_PAGE, required = false, defaultValue = "1") int page,
			@RequestParam(value = PARAM_NB_DISPLAY, required = false, defaultValue = "50") int nbDisplay,
			@RequestParam(value = PARAM_ORDER_BY, required = false, defaultValue = "1") int orderBy,
			@RequestParam(value = PARAM_ASCENDANT, required = false, defaultValue = "true") boolean ascendant,
			@RequestParam(value = PARAM_SEARCH_TYPE, required = false, defaultValue = "0") int searchType,
			@RequestParam(value = PARAM_SEARCH_MOTIF, required = false) String searchMotif) {

		PageWrapper<Computer> wrapper = new PageWrapper<Computer>();
		PageWrapper<ComputerDTO> wrapperDTO = new PageWrapper<ComputerDTO>();

		wrapper = getAttr(page, nbDisplay, orderBy, ascendant, searchType,
				searchMotif);

		/* recuperation de la liste d'ordinateur */
		wrapper = computerService.retrieve(wrapper);

		wrapperDTO = wrapperMapper.computerToDTO(wrapper);

		model.addAttribute("wrapper", wrapperDTO);

		return "dashboard";
	}

}
