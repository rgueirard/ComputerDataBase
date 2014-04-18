package com.excilys.rgueirard.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
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
				
		Criteria critCount = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		critCount.setProjection(Projections.rowCount());
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		
		List<Computer> computers = new ArrayList<Computer>();
		int count = 0;
		

		/* retrieve all */
		if (wrapper.getSearchMotif().isEmpty()) {
			crit.setFetchMode("company", FetchMode.JOIN).createAlias("company", "cpn", JoinType.LEFT_OUTER_JOIN);
			switch (wrapper.getOrderBy()) {
			case 1:
				if(wrapper.isAscendant()){
					crit.addOrder(Order.asc("id"));
				} else {
					crit.addOrder(Order.desc("id"));
				}
				break;
			case 2:
				if(wrapper.isAscendant()){
					crit.addOrder(Order.asc("name"));
				} else {
					crit.addOrder(Order.desc("name"));
				}
				break;
			case 3:
				if(wrapper.isAscendant()){
					crit.addOrder(Order.asc("introduced"));
				} else {
					crit.addOrder(Order.desc("introduced"));
				}
				break;
			case 4:
				if(wrapper.isAscendant()){
					crit.addOrder(Order.asc("discontinued"));
				} else {
					crit.addOrder(Order.desc("discontinued"));
				}
				break;
			case 5:
				if(wrapper.isAscendant()){
					crit.addOrder(Order.asc("company.name"));
				} else {
					crit.addOrder(Order.desc("company.name"));
				}
				break;
			}
						
			count = ((Long)(critCount.uniqueResult())).intValue();
			
			if (count != 0) {
				wrapper.setSize(count);
			}

			/* recuperation de nbPages */
			wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
					/ wrapper.getNbDisplay()));
			
			if (wrapper.getCurrentPage() > wrapper.getNbPages()) {
				wrapper.setCurrentPage(wrapper.getNbPages() - 1);
			}
					
			crit.setMaxResults(wrapper.getNbDisplay()).setFirstResult((wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay());
			computers = crit.list();

			wrapper.setPages(computers);

			/* retrieve by name, by company, by id */
		} else {
			switch (wrapper.getSearchType()) {
			/* by name */
			case 0:
				critCount.setFetchMode("company", FetchMode.JOIN).createAlias("company", "cpn", JoinType.LEFT_OUTER_JOIN);
				critCount.add(Restrictions.like("name" ,"%" + wrapper.getSearchMotif() + "%"));
				crit.setFetchMode("company", FetchMode.JOIN).createAlias("company", "cpn", JoinType.LEFT_OUTER_JOIN);
				crit.add(Restrictions.like("name" ,"%" + wrapper.getSearchMotif() + "%"));
				switch (wrapper.getOrderBy()) {
				case 1:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("id"));
					} else {
						crit.addOrder(Order.desc("id"));
					}
					break;
				case 2:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("name"));
					} else {
						crit.addOrder(Order.desc("name"));
					}
					break;
				case 3:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("introduced"));
					} else {
						crit.addOrder(Order.desc("introduced"));
					}
					break;
				case 4:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("discontinued"));
					} else {
						crit.addOrder(Order.desc("discontinued"));
					}
					break;
				case 5:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("company.name"));
					} else {
						crit.addOrder(Order.desc("company.name"));
					}
					break;
				}
				break;
			/* by company */
			case 1:
				critCount.setFetchMode("company", FetchMode.JOIN).createAlias("company", "cpn", JoinType.INNER_JOIN);
				critCount.add(Restrictions.like("cpn.name" ,"%" + wrapper.getSearchMotif() + "%"));
				crit.setFetchMode("company", FetchMode.JOIN).createAlias("company", "cpn", JoinType.INNER_JOIN);
				crit.add(Restrictions.like("cpn.name" ,"%" + wrapper.getSearchMotif() + "%"));
				switch (wrapper.getOrderBy()) {
				case 1:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("id"));
					} else {
						crit.addOrder(Order.desc("id"));
					}
					break;
				case 2:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("name"));
					} else {
						crit.addOrder(Order.desc("name"));
					}
					break;
				case 3:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("introduced"));
					} else {
						crit.addOrder(Order.desc("introduced"));
					}
					break;
				case 4:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("discontinued"));
					} else {
						crit.addOrder(Order.desc("discontinued"));
					}
					break;
				case 5:
					if(wrapper.isAscendant()){
						crit.addOrder(Order.asc("company.name"));
					} else {
						crit.addOrder(Order.desc("company.name"));
					}
					break;
				}
			}

			/* recupération de la taille */			
			count = ((Long)(critCount.uniqueResult())).intValue();
			
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
				
				crit.setMaxResults(wrapper.getNbDisplay()).setFirstResult((wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay());
				computers = crit.list();
				
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
