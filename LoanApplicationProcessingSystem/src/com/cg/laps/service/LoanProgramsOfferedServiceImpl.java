package com.cg.laps.service;

import com.cg.laps.bean.LoanProgramsOfferedBean;
import com.cg.laps.dao.LoanProgramsOfferedDaoImpl;
import com.cg.laps.exception.LoanProcessingException;

public class LoanProgramsOfferedServiceImpl implements
		ILoanProgramsOfferedService {
	static LoanProgramsOfferedDaoImpl loanProgramsOfferedDao = null;

	@Override
	public int addLoanProgramsOffered(
			LoanProgramsOfferedBean loanProgramsOffered) throws LoanProcessingException {

		loanProgramsOfferedDao = new LoanProgramsOfferedDaoImpl();
		return loanProgramsOfferedDao.addLoanPrograms(loanProgramsOffered);

	}

	@Override
	public int deleteLoanProgramsOffered(String dltprogram) {

		loanProgramsOfferedDao = new LoanProgramsOfferedDaoImpl();
		return loanProgramsOfferedDao.deleteLoanPrograms(dltprogram);

	}

}
