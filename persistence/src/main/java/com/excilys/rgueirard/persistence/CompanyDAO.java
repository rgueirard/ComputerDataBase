package com.excilys.rgueirard.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Company;

@Repository
public class CompanyDAO {

	public CompanyDAO() {
		super();
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	public Company retrieve(long id) {
		Company company = null;
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Company.class);
		crit.add(Restrictions.eq( "id", id ));
		company = (Company) crit.uniqueResult();
		//company = (Company) sessionFactory.getCurrentSession().get(Company.class, id);
		
		return company;
	}

	@SuppressWarnings("unchecked")
	public List<Company> retrieveAll() {
		List<Company> companies = new ArrayList<Company>();
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Company.class);
		//Query query = sessionFactory.getCurrentSession().createQuery("FROM Company cpn");
		//companies = query.list();
		companies = crit.list();
		return companies;
	}

}
