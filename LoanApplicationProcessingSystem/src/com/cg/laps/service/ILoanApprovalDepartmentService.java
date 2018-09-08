package com.cg.laps.service;

import java.util.ArrayList;

import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.exception.LoanProcessingException;

public interface ILoanApprovalDepartmentService {

	String checkLogin(String userId, String password) throws LoanProcessingException;

	ArrayList<LoanApplicationBean> viewSpecificList(String program);

	int changeStatus(int opt, int applicationId) throws LoanProcessingException;

}