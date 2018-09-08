package com.cg.laps.service;

import java.util.ArrayList;

import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.dao.LoanApprovalDepartmentDaoImpl;
import com.cg.laps.dao.LoanProcessingDaoImpl;
import com.cg.laps.exception.LoanProcessingException;

public class LoanApprovalDepartmentServiceImpl implements
		ILoanApprovalDepartmentService {

	LoanProcessingDaoImpl loanDao = null;
	LoanApprovalDepartmentDaoImpl loanApprovalDao = null;

	@Override
	public String checkLogin(String userId, String password) throws LoanProcessingException {
		loanDao = new LoanProcessingDaoImpl();
		return loanDao.checkLogin(userId, password);
	}

	@Override
	public ArrayList<LoanApplicationBean> viewSpecificList(String program) {

		loanApprovalDao = new LoanApprovalDepartmentDaoImpl();
		return loanApprovalDao.viewSpecificList(program);
	}

	@Override
	public int changeStatus(int opt, int applicationId) throws LoanProcessingException {
		loanApprovalDao = new LoanApprovalDepartmentDaoImpl();
		return loanApprovalDao.changeStatus(opt, applicationId);
	}

}
