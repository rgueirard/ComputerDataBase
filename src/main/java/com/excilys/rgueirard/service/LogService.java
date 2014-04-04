package com.excilys.rgueirard.service;

import java.sql.SQLException;
import java.text.ParseException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.rgueirard.persistence.LogDAO;

@Service
@Transactional(readOnly = true)
public class LogService {

	private static Logger logger = LoggerFactory.getLogger(LogDAO.class);

	@Autowired
	private LogDAO logDAO;
	
	@Transactional(readOnly = false)
	public void create(long id, String message) {
		DateTime date = new DateTime();
		java.sql.Date dateSql = new java.sql.Date(date.getMillis());
		
		try {
			logger.info("LogService : creation de log");
			logDAO.create(id, dateSql.toString(), "create");
		} catch (SQLException e) {
			logger.error("LogService : erreur sql dans create");
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error("LogService : erreur de parsage dans create");
			e.printStackTrace();
		}
	}
}
