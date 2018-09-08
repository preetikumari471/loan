package com.cg.laps.dao;

import java.util.ArrayList;

import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.bean.LoanProgramsOfferedBean;
import com.cg.laps.exception.LoanProcessingException;

public interface ILoanProcessingDao {

	public String checkLogin(String userId, String password) throws LoanProcessingException;

	ArrayList<LoanProgramsOfferedBean> display();

	ArrayList<LoanApplicationBean> viewApplicationList();
}
