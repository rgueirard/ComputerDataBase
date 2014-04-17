package com.excilys.rgueirard.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.wrapper.PageWrapper;

@Repository
public class ComputerDAO{

	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	private final String ORDER = "ORDER BY :orderby ";
	private final String ASC = "ASC ";
	private final String DESC = "DESC ";
	private final String INNERJOINCPY = "INNER JOIN cpt.company AS cpy ";
	private final String OUTERJOINCPY = "LEFT JOIN cpt.company AS cpy ";


	public ComputerDAO() {
		super();
	}

	public long delete(long id) {
		logger.debug("ComputerDAO : deletion d'ordinateur");
		Computer computer = Computer.builder().id(id).build();
		sessionFactory.getCurrentSession().delete(computer);
		return id;
	}

	public long update(Computer computer) {
		logger.debug("ComputerDAO : edition d'ordinateur");
		sessionFactory.getCurrentSession().merge(computer);
		return computer.getId();
	}

	public long create(Computer computer) {
		logger.debug("ComputerDAO : creation d'ordinateur");
		sessionFactory.getCurrentSession().persist(computer);
		return computer.getId();
	}

	@SuppressWarnings("unchecked")
	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper) {
		logger.debug("ComputerDAO : recherche d'ordinateurs");
				
		StringBuilder query = new StringBuilder(
				"SELECT cpt FROM Computer AS cpt ");
		StringBuilder sizeQuery = new StringBuilder(
				"SELECT count(*) FROM Computer AS cpt ");
		List<Computer> computers = new ArrayList<Computer>();
		int count = 0;

		/* retrieve all */
		if (wrapper.getSearchMotif().isEmpty()) {
			query.append(OUTERJOINCPY);
			//query.append("WITH cpt.company.id=cpy.id ");
			query.append(ORDER);
			if (wrapper.isAscendant()) {
				query.append(ASC);
			} else {
				query.append(DESC);
			}

			count = ((Long)(sessionFactory.getCurrentSession().createQuery(sizeQuery.toString()).iterate().next())).intValue();
			
			if (count != 0) {
				wrapper.setSize(count);
			}

			/* recuperation de nbPages */
			wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
					/ wrapper.getNbDisplay()));
			
			if (wrapper.getCurrentPage() > wrapper.getNbPages()) {
				wrapper.setCurrentPage(wrapper.getNbPages() - 1);
			}
			
			Query sFQuery = sessionFactory.getCurrentSession().createQuery(query.toString()).setParameter("orderby", wrapper.getOrderBy()).setMaxResults(wrapper.getNbDisplay()).setFirstResult((wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay());
			computers = sFQuery.list();
			wrapper.setPages(computers);

			/* retrieve by name, by company, by id */
		} else {
			switch (wrapper.getSearchType()) {
			/* by name */
			case 0:
				sizeQuery.append(OUTERJOINCPY);
				sizeQuery.append("WHERE cpt.name LIKE :searchmotif ");
				query.append(OUTERJOINCPY);
				query.append("WHERE cpt.name LIKE :searchmotif ");
				query.append(ORDER);
				if (wrapper.isAscendant()) {
					query.append(ASC);
				} else {
					query.append(DESC);
				}
				break;
			/* by company */
			case 1:
				sizeQuery.append(INNERJOINCPY);
				sizeQuery.append("WHERE cpy.name LIKE :searchmotif ");
				query.append(INNERJOINCPY);
				query.append("WHERE cpy.name LIKE :searchmotif ");
				query.append(ORDER);
				if (wrapper.isAscendant()) {
					query.append(ASC);
				} else {
					query.append(DESC);
				}
				break;
			}

			/* recupération de la taille */			
			count = ((Long)(sessionFactory.getCurrentSession().createQuery(sizeQuery.toString()).setParameter("searchmotif" ,"%" + wrapper.getSearchMotif() + "%").iterate().next())).intValue();
			
			if (count != 0) {
				wrapper.setSize(count);
			}
			
			if (wrapper.getSize() != 0) {
				/* recuperation des computers */
						/* recuperation de nbPages */
				wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
						/ wrapper.getNbDisplay()));
				
				if (wrapper.getCurrentPage() > wrapper.getNbPages()) {
					wrapper.setCurrentPage(wrapper.getNbPages() - 1);
				}
				
				Query sFQuery = sessionFactory.getCurrentSession().createQuery(query.toString()).setParameter("searchmotif", "%" + wrapper.getSearchMotif() + "%").setParameter("orderby", wrapper.getOrderBy()).setMaxResults(wrapper.getNbDisplay()).setFirstResult((wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay());
				computers = sFQuery.list();
				wrapper.setPages(computers);
			}
		}
		return wrapper;
	}

	public Computer retrieveById(long id) {
		logger.debug("ComputerDAO : recherche d'ordinateur par id");
		Computer computer = null;
		computer = (Computer)sessionFactory.getCurrentSession().get(Computer.class,id);		
		return computer;
	}
}
