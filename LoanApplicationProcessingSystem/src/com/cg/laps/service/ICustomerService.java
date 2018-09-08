package com.cg.laps.service;

import com.cg.laps.bean.CustomerBean;
import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.exception.LoanProcessingException;

public interface ICustomerService {

	String viewApplicationStatus();

	boolean addPersonalDetails(CustomerBean customer) throws LoanProcessingException;

	int addApplicationDetails(LoanApplicationBean loanApplication) throws LoanProcessingException;

	String viewApplicationStatus(int appid);

}
