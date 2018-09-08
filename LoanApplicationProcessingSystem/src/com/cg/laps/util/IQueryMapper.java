package com.cg.laps.util;

public class IQueryMapper {

	public static final String VALID_LOGIN = "SELECT role FROM Users WHERE login_id=? and password=?";
	public static final String RETRIEVE_LOAN_PROGRAMS_OFFERED_LIST = "SELECT * FROM loanProgramsOffered";
	public static final String RETRIEVE_LOAN_APPLICATION_STATUS = "SELECT status FROM loanApplication WHERE application_id = 1001";
	public static final String INSERT_QUERY_CUSTOMER_DETAILS = "INSERT INTO CustomerDetails VALUES (?,?, TO_DATE(?, 'dd-mm-yyyy'),?,?,?,?,?)";
	public static final String APPLICATIONID_QUERY_SEQUENCE = "SELECT Application_ID_sequence.CURRVAL FROM DUAL";
	public static final String INSERT_QUERY_APPLICATION_DETAILS = "INSERT INTO LoanApplication VALUES (Application_Id_sequence.NEXTVAL,SYSDATE,?,?,?,?,?,?,?,'Applied',SYSDATE+7)";
	public static final String INSERT_QUERY_LOAN_PROGRAMS_OFFERED = "INSERT INTO LoanProgramsOffered VALUES (?,?,?,?,?,?,?,?)";
	public static final String RETRIEVE_LOAN_SPECIFIC_APPLICATIONS = "SELECT Application_ID,to_char(application_Date,'dd-mm-yyyy'),Loan_Program,AmountOfLoan,AddressOfProperty,AnnualFamilyIncome,DocumentProofsAvailable,GuaranteeCover,MarketValueOfGuaranteeCover,status,to_char(dateOfInterview,'dd-mm-yyyy') FROM loanApplication WHERE loan_program=?";
	public static final String DELETE_QUERY_LOAN_PROGRAMS_OFFERED = "DELETE FROM LOANPROGRAMSOFFERED WHERE ProgramName=?";
	public static final String VIEW_STATUS = "SELECT status FROM LoanApplication WHERE Application_Id=?";
	public static final String VIEW_LOAN_APPLICATIONS = "SELECT Application_ID,to_char(application_Date,'dd-mm-yyyy'),Loan_Program,AmountOfLoan,AddressOfProperty,AnnualFamilyIncome,DocumentProofsAvailable,GuaranteeCover,MarketValueOfGuaranteeCover,status,to_char(dateOfInterview,'dd-mm-yyyy') FROM loanApplication";
	public static final String UPDATE_QUERY_APPLICATION_STATUS_ACCEPT = "UPDATE LoanApplication SET Status='Accepted' WHERE Application_ID=?";
	public static final String UPDATE_QUERY_APPLICATION_STATUS_APPROVE = "UPDATE LoanApplication SET Status='Approved' WHERE Application_ID=?";
	public static final String UPDATE_QUERY_APPLICATION_STATUS_REJECT = "UPDATE loanApplication SET Status='Rejected' WHERE Application_ID=?";
}
