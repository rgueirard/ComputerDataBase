package com.excilys.rgueirard.persistence;

import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Log;

@Repository
public class LogDAO {

	public LogDAO() {
		super();
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	public void create(long computerId, String message) {

		DateTime time = new DateTime();
		
		Log log = Log.builder().computerId(computerId).message(message).time(time).build();
		
		sessionFactory.getCurrentSession().persist(log);		
	}
}
