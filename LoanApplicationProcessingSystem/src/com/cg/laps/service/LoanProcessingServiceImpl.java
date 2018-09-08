package com.cg.laps.service;

import java.util.ArrayList;

import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.bean.LoanProgramsOfferedBean;
import com.cg.laps.dao.AdminDaoImpl;
import com.cg.laps.dao.LoanApprovalDepartmentDaoImpl;
import com.cg.laps.dao.LoanProcessingDaoImpl;
import com.cg.laps.exception.LoanProcessingException;

public class LoanProcessingServiceImpl implements ILoanProcessingService {

	static LoanProcessingDaoImpl loanProcessingDao = null;

	@Override
	public String checkLogin(String userId, String password) throws LoanProcessingException {
		loanProcessingDao = new LoanProcessingDaoImpl();
		return loanProcessingDao.checkLogin(userId, password);
	}

	@Override
	public ArrayList<LoanProgramsOfferedBean> display() {
		loanProcessingDao = new LoanProcessingDaoImpl();
		return loanProcessingDao.display();

	}

	@Override
	public ArrayList<LoanApplicationBean> viewApplicationList() {
		loanProcessingDao = new LoanProcessingDaoImpl();
		return loanProcessingDao.viewApplicationList();
	}

}
