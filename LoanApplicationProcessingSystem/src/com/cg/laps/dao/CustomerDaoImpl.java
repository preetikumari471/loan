package com.cg.laps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.laps.bean.CustomerBean;
import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.exception.LoanProcessingException;
import com.cg.laps.util.DBUtil;
import com.cg.laps.util.IQueryMapper;

public class CustomerDaoImpl implements ICustomerDao {
	static Connection conn;
	static PreparedStatement preparedstatement = null;
	static ResultSet rs = null;
	boolean result = false;
	Logger logger = Logger.getRootLogger();

	public CustomerDaoImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	@Override
	public int addApplicationDetails(LoanApplicationBean loanApplication)
			throws LoanProcessingException {

		int queryResult = 0;
		int applicationId = 0;
		String generatedColumns[] = { "APPLICATION_ID"};
		conn = DBUtil.establishConnection();
		try {
			preparedstatement = conn.prepareStatement(IQueryMapper.INSERT_QUERY_APPLICATION_DETAILS, generatedColumns);

			preparedstatement.setString(1, loanApplication.getLoanProgram());
			preparedstatement.setDouble(2, loanApplication.getLoanAmount());
			preparedstatement.setString(3, loanApplication.getPropertyAddress());
			preparedstatement.setDouble(4, loanApplication.getAnnualFamilyIncome());
			preparedstatement.setString(5, loanApplication.getDocsProof());
			preparedstatement.setString(6, loanApplication.getGuaranteeCoverString());
			preparedstatement.setDouble(7, loanApplication.getMarktValOfCover());

			int affectedRows = preparedstatement.executeUpdate();
			
			if (affectedRows == 0) 
	            throw new LoanProcessingException("Creating User's Account Failed");
			
			try (ResultSet generatedKeys = preparedstatement.getGeneratedKeys()) {
	             if (generatedKeys.next()) 
	            	 applicationId = generatedKeys.getInt(1);
	             
	         	} 
	         catch (SQLException e) {
	        	 throw new LoanProcessingException("Error Geeting Application ID for addded customer");
	         }
			}

		 catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new LoanProcessingException(
					"Tehnical problem occured refer log");
		}

		finally {
			try {
				preparedstatement.close();
				conn.close();
			}

			catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new LoanProcessingException(
						"Error in closing db connection");

			}
		}

		return applicationId;
	}

	@Override
	public boolean addPersonalDetails(CustomerBean customer)
			throws LoanProcessingException {

		int queryResult = 0;

		conn = DBUtil.establishConnection();
		try {
			preparedstatement = conn
					.prepareStatement(IQueryMapper.INSERT_QUERY_CUSTOMER_DETAILS);

			preparedstatement.setInt(1, customer.getApplicationId());
			preparedstatement.setString(2, customer.getApplicantName());
			preparedstatement.setString(3, customer.getDateOfBirth());
			preparedstatement.setString(4, customer.getMaritalStatus());
			preparedstatement.setLong(5, customer.getPhoneNumber());
			preparedstatement.setLong(6, customer.getMobileNumber());
			preparedstatement.setDouble(7, customer.getDependentsCount());
			preparedstatement.setString(8, customer.getEmailId());

			queryResult = preparedstatement.executeUpdate();

			if (queryResult == 1) {
				logger.info("Customer details added successfully!!");

			} else {
				
				logger.error("Insertion failed ");
				throw new LoanProcessingException(
						"Inserting customer details failed ");
				
			}

		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new LoanProcessingException(
					"Tehnical problem occured refer log");
		}

		finally {
			try {
				preparedstatement.close();
				conn.close();
			}

			catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new LoanProcessingException(
						"Error in closing db connection");

			}
		}

		return true;
	}

	@Override
	public String viewApplicationStatus(int appid) {

		String appstatus = null;
		try {
			conn = DBUtil.establishConnection();
			preparedstatement = conn.prepareStatement(IQueryMapper.VIEW_STATUS);
			preparedstatement.setInt(1, appid);
			ResultSet rs = preparedstatement.executeQuery();
			while (rs.next()) {
				appstatus = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appstatus;
	}

}
