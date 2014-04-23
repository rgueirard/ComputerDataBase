package com.excilys.rgueirard.service;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.rgueirard.domain.Log;
import com.excilys.rgueirard.persistence.LogDAO;

@Service
@Transactional(readOnly = true)
public class LogService {

	private static Logger logger = LoggerFactory.getLogger(LogDAO.class);

	@Autowired
	private LogDAO logDAO;
	
	@Transactional(readOnly = false)
	public void create(long id, String message) {
		DateTime time = new DateTime();
		Log log = null;
		logger.info("LogService : creation de log");
		log = Log.builder().computerId(id).message(message).time(time).build();
		logDAO.save(log);
		
	}
}
