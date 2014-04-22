package com.excilys.rgueirard.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.QCompany;
import com.mysema.query.jpa.hibernate.HibernateQuery;


@Repository
public class CompanyDAO {

	public CompanyDAO() {
		super();
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	public Company retrieve(long id) {
		Company company = null;
		
		QCompany qCompany = QCompany.company;
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		
		company = query.from(qCompany).where(qCompany.id.eq(id)).uniqueResult(qCompany);
			
		return company;
	}

	public List<Company> retrieveAll() {
		List<Company> companies = new ArrayList<Company>();
		
		QCompany qCompany = QCompany.company;
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		
		companies = query.from(qCompany).list(qCompany);
		return companies;
	}

}
