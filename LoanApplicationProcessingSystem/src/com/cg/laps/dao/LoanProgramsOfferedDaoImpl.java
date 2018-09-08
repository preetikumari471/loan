package com.cg.laps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.laps.bean.LoanProgramsOfferedBean;
import com.cg.laps.bean.UserBean;
import com.cg.laps.exception.LoanProcessingException;
import com.cg.laps.util.DBUtil;
import com.cg.laps.util.IQueryMapper;

public class LoanProgramsOfferedDaoImpl implements ILoanProgramsOfferedDao {

	static Connection conn;
	static PreparedStatement preparedstatement = null;
	static ResultSet rs = null;
	int operation = 0;
	
	Logger logger=Logger.getRootLogger();
	
	public LoanProgramsOfferedDaoImpl() {
	PropertyConfigurator.configure("resources//log4j.properties");
	
	}

	@Override
	public int addLoanPrograms(LoanProgramsOfferedBean loanProgramsOffered) throws LoanProcessingException {

		conn = DBUtil.establishConnection();
		try {
			preparedstatement = conn
					.prepareStatement(IQueryMapper.INSERT_QUERY_LOAN_PROGRAMS_OFFERED);

			preparedstatement.setString(1,
					loanProgramsOffered.getLoanProgramName());
			preparedstatement
					.setString(2, loanProgramsOffered.getDescription());
			preparedstatement.setString(3, loanProgramsOffered.getLoanType());
			preparedstatement.setInt(4,
					loanProgramsOffered.getDurationInYears());
			preparedstatement
					.setDouble(5, loanProgramsOffered.getMinLoanAmnt());
			preparedstatement
					.setDouble(6, loanProgramsOffered.getMaxLoanAmnt());
			preparedstatement.setDouble(7,
					loanProgramsOffered.getRateOfIntrest());
			preparedstatement.setString(8, loanProgramsOffered.getProofReq());

			operation = preparedstatement.executeUpdate();

			
			if(operation==0) { logger.error("Insertion failed "); throw new
			LoanProcessingException("Adding new loan program failed ");
			 
			 } else { logger.info("New loan program added successfully:"); 
			 }
			  
			  } catch(SQLException sqlException) {
			 logger.error(sqlException.getMessage()); throw new
		LoanProcessingException("Tehnical problem occured refer log"); }
			 

			 finally
			{
			try
			 {
			rs.close();
			preparedstatement.close();
			conn.close();
			}
		
			 catch (SQLException sqlException) {
			 logger.error(sqlException.getMessage()); throw new
			 LoanProcessingException("Error in closing db connection");
			
			 }

			}

		return operation;
	}

	@Override
	public int deleteLoanPrograms(String dltprogram) {
		try {
			String status = null;
			;
			conn = DBUtil.establishConnection();

			preparedstatement = conn
					.prepareStatement(IQueryMapper.DELETE_QUERY_LOAN_PROGRAMS_OFFERED);
			preparedstatement.setString(1, dltprogram);

			operation = preparedstatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return operation;
	}

}
