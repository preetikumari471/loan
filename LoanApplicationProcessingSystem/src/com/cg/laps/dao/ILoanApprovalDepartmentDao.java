package com.cg.laps.dao;

import java.util.ArrayList;

import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.exception.LoanProcessingException;

public interface ILoanApprovalDepartmentDao {

	ArrayList<LoanApplicationBean> viewSpecificList(String program) throws LoanProcessingException;

	int changeStatus(int opt, int applicationId) throws LoanProcessingException;

}
