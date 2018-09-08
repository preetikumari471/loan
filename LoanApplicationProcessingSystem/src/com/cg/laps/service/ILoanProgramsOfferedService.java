package com.cg.laps.service;

import com.cg.laps.bean.LoanProgramsOfferedBean;
import com.cg.laps.exception.LoanProcessingException;

public interface ILoanProgramsOfferedService {

	int addLoanProgramsOffered(LoanProgramsOfferedBean loanProgramsOffered) throws LoanProcessingException;

	int deleteLoanProgramsOffered(String dltprogram);

}
