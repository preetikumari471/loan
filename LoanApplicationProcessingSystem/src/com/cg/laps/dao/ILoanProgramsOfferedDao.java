package com.cg.laps.dao;

import com.cg.laps.bean.LoanProgramsOfferedBean;
import com.cg.laps.exception.LoanProcessingException;

public interface ILoanProgramsOfferedDao {

	int addLoanPrograms(LoanProgramsOfferedBean loanProgramsOffered) throws LoanProcessingException;

	int deleteLoanPrograms(String dltprogram);

}
