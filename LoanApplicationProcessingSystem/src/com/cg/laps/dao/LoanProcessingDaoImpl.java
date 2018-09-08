package com.cg.laps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.bean.LoanProgramsOfferedBean;
import com.cg.laps.bean.UserBean;
import com.cg.laps.exception.LoanProcessingException;
import com.cg.laps.util.DBUtil;
import com.cg.laps.util.IQueryMapper;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

public class LoanProcessingDaoImpl implements ILoanProcessingDao {

	Logger logger = Logger.getRootLogger();

	public LoanProcessingDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	String role = null;
	static Connection conn;
	static PreparedStatement preparedstatement = null;
	static ResultSet rs = null;

	@Override
	public String checkLogin(String userId, String password) throws LoanProcessingException {

		try {
			conn = DBUtil.establishConnection();
			preparedstatement = conn.prepareStatement(IQueryMapper.VALID_LOGIN);
			preparedstatement.setString(1, userId);
			preparedstatement.setString(2, password);
			ResultSet rs = preparedstatement.executeQuery();
			while (rs.next()) {
				role = rs.getString(1);

			}
			if (role != null)
			{ 
				logger.info("Login successful for "+role);
			 } else {  
			 logger.error("Login failed "); throw new
				LoanProcessingException("Invalid username or password!!!! ");
				 
			 }
			  
			  } catch(SQLException sqlException) {
			 logger.error(sqlException.getMessage()); throw new
		LoanProcessingException("Tehnical problem occured refer log"); }
				
		
		return role;
	}

	@Override
	public ArrayList<LoanProgramsOfferedBean> display() {
		int loanProgramsCount = 0;
		conn = DBUtil.establishConnection();
		try {
			ArrayList<LoanProgramsOfferedBean> loanProgramsOfferedList = new ArrayList<LoanProgramsOfferedBean>();
			preparedstatement = conn
					.prepareStatement(IQueryMapper.RETRIEVE_LOAN_PROGRAMS_OFFERED_LIST);
			rs = preparedstatement.executeQuery();
			while (rs.next()) {
				LoanProgramsOfferedBean loanProgramsOffered = new LoanProgramsOfferedBean();
				loanProgramsOffered.setLoanProgramName(rs.getString(1));
				loanProgramsOffered.setDescription(rs.getString(2));
				loanProgramsOffered.setLoanType(rs.getString(3));
				loanProgramsOffered.setDurationInYears(rs.getInt(4));
				loanProgramsOffered.setMinLoanAmnt(rs.getDouble(5));
				loanProgramsOffered.setMaxLoanAmnt(rs.getDouble(6));
				loanProgramsOffered.setRateOfIntrest(rs.getDouble(7));
				loanProgramsOffered.setProofReq(rs.getString(8));
				loanProgramsOfferedList.add(loanProgramsOffered);

				loanProgramsCount++;
			}
			if (loanProgramsCount == 0)
				return null;
			else
				return loanProgramsOfferedList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public ArrayList<LoanApplicationBean> viewApplicationList() {
		ArrayList<LoanApplicationBean> loanApplicationList = new ArrayList<LoanApplicationBean>();
		int applicationDetailsCount = 0;
		conn = DBUtil.establishConnection();
		try {
			preparedstatement = conn
					.prepareStatement(IQueryMapper.VIEW_LOAN_APPLICATIONS);
			rs = preparedstatement.executeQuery();
			while (rs.next()) {
				LoanApplicationBean loanApplication = new LoanApplicationBean();
				loanApplication.setApplicationId(rs.getInt(1));
				loanApplication.setApplicationDate(rs.getString(2));
				loanApplication.setLoanProgram(rs.getString(3));
				loanApplication.setLoanAmount(rs.getDouble(4));
				loanApplication.setPropertyAddress(rs.getString(5));
				loanApplication.setAnnualFamilyIncome(rs.getDouble(6));
				loanApplication.setDocsProof(rs.getString(7));
				loanApplication.setGuaranteeCoverString(rs.getString(8));
				loanApplication.setMarktValOfCover(rs.getDouble(9));
				loanApplication.setStatus(rs.getString(10));
				loanApplication.setInterviewDate(rs.getString(11));
				loanApplicationList.add(loanApplication);
				applicationDetailsCount++;
			}
			if (applicationDetailsCount == 0)
				return null;
			else
				return loanApplicationList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
