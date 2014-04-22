package com.excilys.rgueirard.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.domain.QCompany;
import com.excilys.rgueirard.domain.QComputer;
import com.excilys.rgueirard.wrapper.PageWrapper;
import com.mysema.query.jpa.hibernate.HibernateQuery;

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

	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper) {
		logger.debug("ComputerDAO : recherche d'ordinateurs");
				
		QComputer qComputer = QComputer.computer;
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		HibernateQuery queryCount = new HibernateQuery(sessionFactory.getCurrentSession());
				
		List<Computer> computers = new ArrayList<Computer>();
		int count = 0;
		

		/* retrieve all */
		if (wrapper.getSearchMotif().isEmpty()) {
			query.leftJoin(qComputer.company, QCompany.company);
			switch (wrapper.getOrderBy()) {
			case 1:
				if(wrapper.isAscendant()){
					query.orderBy(qComputer.id.asc());
				} else {
					query.orderBy(qComputer.id.desc());
				}
				break;
			case 2:
				if(wrapper.isAscendant()){
					query.orderBy(qComputer.name.asc());
				} else {
					query.orderBy(qComputer.name.desc());
				}
				break;
			case 3:
				if(wrapper.isAscendant()){
					query.orderBy(qComputer.introduced.asc());
				} else {
					query.orderBy(qComputer.introduced.desc());
				}
				break;
			case 4:
				if(wrapper.isAscendant()){
					query.orderBy(qComputer.discontinued.asc());
				} else {
					query.orderBy(qComputer.discontinued.desc());
				}
				break;
			case 5:
				if(wrapper.isAscendant()){
					query.orderBy(qComputer.company.name.asc());
				} else {
					query.orderBy(qComputer.company.name.desc());
				}
				break;
			}
						
			count = ((Long)queryCount.count()).intValue();
			
			if (count != 0) {
				wrapper.setSize(count);
			}

			/* recuperation de nbPages */
			wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
					/ wrapper.getNbDisplay()));
			
			if (wrapper.getCurrentPage() > wrapper.getNbPages()) {
				wrapper.setCurrentPage(wrapper.getNbPages() - 1);
			}
				
			query.offset((wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay()).limit(wrapper.getNbDisplay());
			computers = query.list(QComputer.computer);

			wrapper.setPages(computers);

			/* retrieve by name, by company, by id */
		} else {
			switch (wrapper.getSearchType()) {
			/* by name */
			case 0:
				queryCount.leftJoin(qComputer.company, QCompany.company);
				queryCount.where(qComputer.name.like("%" + wrapper.getSearchMotif() + "%"));
				query.leftJoin(qComputer.company, QCompany.company);
				query.where(qComputer.name.like("%" + wrapper.getSearchMotif() + "%"));
				
				switch (wrapper.getOrderBy()) {
				case 1:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.id.asc());
					} else {
						query.orderBy(qComputer.id.desc());
					}
					break;
				case 2:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.name.asc());
					} else {
						query.orderBy(qComputer.name.desc());
					}
					break;
				case 3:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.introduced.asc());
					} else {
						query.orderBy(qComputer.introduced.desc());
					}
					break;
				case 4:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.discontinued.asc());
					} else {
						query.orderBy(qComputer.discontinued.desc());
					}
					break;
				case 5:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.company.name.asc());
					} else {
						query.orderBy(qComputer.company.name.desc());
					}
					break;
				}
				break;
			/* by company */
			case 1:
				queryCount.innerJoin(qComputer.company, QCompany.company);
				queryCount.where(qComputer.company.name.like("%" + wrapper.getSearchMotif() + "%"));
				query.innerJoin(qComputer.company, QCompany.company);
				query.where(qComputer.company.name.like("%" + wrapper.getSearchMotif() + "%"));
				switch (wrapper.getOrderBy()) {
				case 1:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.id.asc());
					} else {
						query.orderBy(qComputer.id.desc());
					}
					break;
				case 2:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.name.asc());
					} else {
						query.orderBy(qComputer.name.desc());
					}
					break;
				case 3:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.introduced.asc());
					} else {
						query.orderBy(qComputer.introduced.desc());
					}
					break;
				case 4:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.discontinued.asc());
					} else {
						query.orderBy(qComputer.discontinued.desc());
					}
					break;
				case 5:
					if(wrapper.isAscendant()){
						query.orderBy(qComputer.company.name.asc());
					} else {
						query.orderBy(qComputer.company.name.desc());
					}
					break;
				}
				break;
			}

			/* recupération de la taille */			
			count = ((Long)queryCount.count()).intValue();
			
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
				
				query.offset((wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay()).limit(wrapper.getNbDisplay());
				computers = query.list(QComputer.computer);
				
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
