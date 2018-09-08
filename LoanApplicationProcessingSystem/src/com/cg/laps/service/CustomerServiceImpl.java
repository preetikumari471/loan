package com.cg.laps.service;

import com.cg.laps.bean.CustomerBean;
import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.dao.CustomerDaoImpl;
import com.cg.laps.exception.LoanProcessingException;

public class CustomerServiceImpl implements ICustomerService {

	static CustomerDaoImpl customerDao = null;

	@Override
	public String viewApplicationStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addApplicationDetails(LoanApplicationBean loanApplication) throws LoanProcessingException {
		customerDao = new CustomerDaoImpl();
		return customerDao.addApplicationDetails(loanApplication);

	}

	@Override
	public boolean addPersonalDetails(CustomerBean customer) throws LoanProcessingException {
		customerDao = new CustomerDaoImpl();
		return customerDao.addPersonalDetails(customer);
	}

	@Override
	public String viewApplicationStatus(int appid) {
		customerDao = new CustomerDaoImpl();
		return customerDao.viewApplicationStatus(appid);
	}

}