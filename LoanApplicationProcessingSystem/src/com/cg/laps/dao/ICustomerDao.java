package com.cg.laps.dao;

import com.cg.laps.bean.CustomerBean;
import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.exception.LoanProcessingException;

public interface ICustomerDao {

	boolean addPersonalDetails(CustomerBean customer) throws LoanProcessingException;

	int addApplicationDetails(LoanApplicationBean loanApplication) throws LoanProcessingException;

	String viewApplicationStatus(int appid);

}
