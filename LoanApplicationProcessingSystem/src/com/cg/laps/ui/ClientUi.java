package com.cg.laps.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.laps.bean.LoanApplicationBean;
import com.cg.laps.bean.LoanProgramsOfferedBean;
import com.cg.laps.bean.UserBean;
import com.cg.laps.bean.CustomerBean;
import com.cg.laps.dao.LoanProcessingDaoImpl;
import com.cg.laps.exception.LoanProcessingException;
import com.cg.laps.service.AdminServiceImpl;
import com.cg.laps.service.CustomerServiceImpl;
import com.cg.laps.service.ICustomerService;
import com.cg.laps.service.ILoanApprovalDepartmentService;
import com.cg.laps.service.LoanApprovalDepartmentServiceImpl;
import com.cg.laps.service.LoanProcessingServiceImpl;
import com.cg.laps.service.LoanProgramsOfferedServiceImpl;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class ClientUi {

	static Scanner scanner = null;
	static CustomerBean customer = null;
	static UserBean user = null;
	static CustomerServiceImpl customerService = null;
	static LoanApprovalDepartmentServiceImpl loanApprovalDepartmentService = null;
	static LoanProgramsOfferedServiceImpl loanProgramsOfferedService = null;
	static ClientUi client = null;
	static LoanProgramsOfferedBean loanProgramsOffered = null;
	static LoanApplicationBean loanApplication = null;
	static LoanProcessingServiceImpl loanProcessingService = null;
	static AdminServiceImpl adminService = null;
	static Logger logger = Logger.getRootLogger();

		

	public static void main(String[] args) throws LoanProcessingException {
		PropertyConfigurator.configure("resources//log4j.properties");
		
		
		loanApprovalDepartmentService = new LoanApprovalDepartmentServiceImpl();
		loanProcessingService = new LoanProcessingServiceImpl();
		customerService = new CustomerServiceImpl();
		loanProgramsOfferedService = new LoanProgramsOfferedServiceImpl();
		loanProgramsOffered = new LoanProgramsOfferedBean();
		loanApplication = new LoanApplicationBean();
		customer = new CustomerBean();
		user = new UserBean();
		adminService = new AdminServiceImpl();
		ArrayList<LoanApplicationBean> loanApplicationList = new ArrayList<LoanApplicationBean>();

		scanner = new Scanner(System.in);
		boolean status = false;
		String role = null;
		String userId, password;
		int appid = 0;
		String appstatus = null;

		do {
			//menu
			System.out
					.println("-------------LOAN APPLICATION PROCESSING SYSTEM-----------");
			System.out
					.println("__________________________________________________________");
			System.out.println("Select your choice to proceed:");
			System.out.println("1. Login as Admin/Member of LAD");
			System.out.println("2. Enter as Customer");
			System.out.println(" Press any other key to exit ");
			
			//accept valid input
			switch (getValidUserInput()) {
			case 1:

				System.out.println("Enter Username: ");
				userId = scanner.nextLine();
				System.out.println("Enter Password:");
				password = scanner.nextLine();

				loanProcessingService = new LoanProcessingServiceImpl();
				role = loanProcessingService.checkLogin(userId, password);
				if (role != null)
					status = true;
				else
					status = false;
				break;

			case 2:

				do {
					System.out.println();
					System.out
							.println("--------------- Welcome ---------------");
					System.out.println("Select your choice :");
					System.out.println("1. View loan programs");
					System.out.println("2. View application status");
					System.out.println(" Press any other key to exit ");

					switch (getValidUserInput()) {
					case 1:
						int applicationId = 0;
						System.out
								.println("-------AVAILABLE LOAN PROGRAMS------");
						client = new ClientUi();
						client.displayLoanPrograms();
						System.out
								.println("_______________________________________");
						System.out.println("Apply for loan?(Y/N)");
						String input = scanner.next();
						while (input.equalsIgnoreCase("Y")) {
							if (loanProcessingService.display() != null) {

								loanApplication = populateLoanApplicationBean();
								applicationId = customerService
										.addApplicationDetails(loanApplication);
								System.out.println();
								System.out.println("Your application ID is:"
										+ applicationId);

								customer = populateCustomerBean();
								boolean add = customerService
										.addPersonalDetails(customer);
								if (add == true) {
									System.out
											.println("Details added successfully!!!");
								} else {
									System.err.println("Error encountered. Please Try again later!");
								}
							}

						}
						break;

					case 2:
						System.out.println("Enter Application ID: ");
						appid = scanner.nextInt();

						customerService = new CustomerServiceImpl();
						appstatus = customerService
								.viewApplicationStatus(appid);
						if (appstatus != null)
							System.out.println("Your current status is: "
									+ appstatus);
						else
							System.out.println("No loan application found!!");
						break;

					default:
						System.out.println("You have successfully exited!!");
						System.exit(0);
					}

				} while (true);

			default:
				System.out.println("You have successfully exited!!");
				System.exit(0);

			}
			if (status == false) {
				System.err.println("Invalid username or password!!!");
			} else {
				break;
			}

		} while (true);

		if (status == true) {
			if (role.equalsIgnoreCase("admin")) {
				do {
					System.out.println();
					System.out
							.println("--------------- Welcome Admin ---------------");
					System.out.println("Select your choice :");
					System.out.println("1. View loan programs");
					System.out.println("2. Update loan programs");
					System.out.println("3) Generate Reports");
					System.out.println(" Press any other key to exit ");

					switch (getValidUserInput()) {
					case 1:
						client = new ClientUi();
						client.displayLoanPrograms();
						break;

					case 2:
						System.out.println();
						System.out
								.println("--------------- Update loan programs ---------------");
						System.out.println("Select your choice :");
						System.out.println("1) Add Loan Programs");
						System.out.println("2) Delete Loan Programs");
						System.out.println(" Press any other key to exit ");
						int operation = 0;
						String dltprogram = null;
						switch (getValidUserInput()) {

						case 1:

							loanProgramsOffered = populateLoanProgramsOfferedBean();

							operation = loanProgramsOfferedService
									.addLoanProgramsOffered(loanProgramsOffered);
							if (operation == 1) {
								System.out
										.println("Data added successfully!!!");
							} else {
								System.out
										.println("Error encountered. Please Try again later!");
							}
							break;
						case 2:

							System.out
									.println("Enter the name of loan program: ");
							dltprogram = scanner.next();
							operation = loanProgramsOfferedService
									.deleteLoanProgramsOffered(dltprogram);
							if (operation == 1) {
								System.out
										.println("Data deleted successfully!!!");
							} else {
								System.out
										.println("Error encountered. Please Try again later!");
							}
							break;

						default:
							System.out
									.println("You have successfully exited!!");
							System.exit(0);
						}
						break;

					case 3:
						System.out.println();
						System.out
								.println("--------------- Generate Reports ---------------");
						System.out.println("Choose Your Option :");
						System.out.println("1. List of applications");
						System.out.println("2. View status of applications");
						System.out.println(" Press any other key to exit ");
						switch (getValidUserInput()) {
						case 1:

							loanApplicationList = loanProcessingService
									.viewApplicationList();

							if (loanApplicationList != null) {
								Iterator<LoanApplicationBean> i = loanApplicationList
										.iterator();
								while (i.hasNext()) {
									loanApplication = i.next();
									System.out.println("Application ID : "
											+ loanApplication
													.getApplicationId());
									System.out.println("Application Date : "
											+ loanApplication
													.getApplicationDate());
									System.out.println("Loan Program : "
											+ loanApplication.getLoanProgram());
									System.out.println("Loan Amount : "
											+ loanApplication.getLoanAmount());
									System.out.println("Address of Property : "
											+ loanApplication
													.getPropertyAddress());
									System.out
											.println("Annual Family Income : "
													+ loanApplication
															.getAnnualFamilyIncome());
									System.out.println("Document proof : "
											+ loanApplication.getDocsProof());
									System.out.println("Guarantee Cover : "
											+ loanApplication
													.getGuaranteeCoverString());
									System.out
											.println("Market value of guarantee cover : "
													+ loanApplication
															.getMarktValOfCover());
									System.out.println("Application status : "
											+ loanApplication.getStatus());
									System.out.println("Interview Date : "
											+ loanApplication
													.getInterviewDate());
									System.out
											.println("----------------------------------------------------------");

								}

							} else {
								System.out.println("End of list!!");
							}

							break;

						case 2:
							/*
							 * ArrayList<LoanApplicationBean> loanStatusList=new
							 * ArrayList<LoanApplicationBean>();
							 * loanStatusList=adminService
							 * .viewApplicationList();
							 * 
							 * if(loanStatusList !=null){
							 * Iterator<LoanApplicationBean>
							 * i=loanStatusList.iterator(); while(i.hasNext()){
							 * loanApplication=i.next();
							 * System.out.println("Application ID : "
							 * +loanApplication.getApplicationId());
							 * System.out.println
							 * ("Application Date : "+loanApplication
							 * .getApplicationDate());
							 * System.out.println("Loan Program : "
							 * +loanApplication.getLoanProgram());
							 * System.out.println
							 * ("Loan Amount : "+loanApplication
							 * .getLoanAmount());
							 * System.out.println("Address of Property : "
							 * +loanApplication.getPropertyAddress());
							 * System.out
							 * .println("Annual Family Income : "+loanApplication
							 * .getAnnualFamilyIncome());
							 * System.out.println("Document proof : "
							 * +loanApplication.getDocsProof());
							 * System.out.println
							 * ("Guarantee Cover : "+loanApplication
							 * .getGuaranteeCoverString()); System.out.println(
							 * "Market value of guarantee cover : "
							 * +loanApplication.getMarktValOfCover());
							 * System.out
							 * .println("Application status : "+loanApplication
							 * .getStatus());
							 * System.out.println("Interview Date : "
							 * +loanApplication.getInterviewDate());
							 * System.out.println(
							 * "----------------------------------------------------------"
							 * );
							 * 
							 * }
							 * 
							 * } else { System.out.println("End of list!!"); }
							 */

							break;

						}

						break;

					default:
						System.out.println("You have successfully exited!!");
						System.exit(0);
					}
				} while (true);
			}

			else {
				do {
					System.out.println();
					System.out
							.println("--------------- Welcome Member of Loan Approval Department ---------------");
					System.out.println("Select your choice :");
					System.out.println("1. View loan programs");
					System.out
							.println("2. View applications for specific loan program");
					System.out.println("3. Accept/reject loan aplications");
					System.out.println(" Press any other key to exit ");
					String program = null;

					switch (getValidUserInput()) {
					case 1:
						client = new ClientUi();
						client.displayLoanPrograms();
						break;

					case 2:

						loanApplicationList = loanProcessingService
								.viewApplicationList();

						if (loanApplicationList != null) {
							Iterator<LoanApplicationBean> i = loanApplicationList
									.iterator();
							while (i.hasNext()) {
								loanApplication = i.next();
								System.out.println("Application ID : "
										+ loanApplication.getApplicationId());
								System.out.println("Application Date : "
										+ loanApplication.getApplicationDate());
								System.out.println("Loan Program : "
										+ loanApplication.getLoanProgram());
								System.out.println("Loan Amount : "
										+ loanApplication.getLoanAmount());
								System.out.println("Address of Property : "
										+ loanApplication.getPropertyAddress());
								System.out.println("Annual Family Income : "
										+ loanApplication
												.getAnnualFamilyIncome());
								System.out.println("Document proof : "
										+ loanApplication.getDocsProof());
								System.out.println("Guarantee Cover : "
										+ loanApplication
												.getGuaranteeCoverString());
								System.out
										.println("Market value of guarantee cover : "
												+ loanApplication
														.getMarktValOfCover());
								System.out.println("Application status : "
										+ loanApplication.getStatus());
								System.out.println("Interview Date : "
										+ loanApplication.getInterviewDate());
								System.out
										.println("----------------------------------------------------------");

							}

						} else {
							System.out.println("End of list!!");
						}

						break;

					case 3:
						loanApplicationList = loanProcessingService
								.viewApplicationList();

						if (loanApplicationList != null) {
							Iterator<LoanApplicationBean> i = loanApplicationList
									.iterator();
							while (i.hasNext()) {
								loanApplication = i.next();
								System.out.println("Application ID : "
										+ loanApplication.getApplicationId());
								System.out.println("Application Date : "
										+ loanApplication.getApplicationDate());
								System.out.println("Loan Program : "
										+ loanApplication.getLoanProgram());
								System.out.println("Loan Amount : "
										+ loanApplication.getLoanAmount());
								System.out.println("Address of Property : "
										+ loanApplication.getPropertyAddress());
								System.out.println("Annual Family Income : "
										+ loanApplication
												.getAnnualFamilyIncome());
								System.out.println("Document proof : "
										+ loanApplication.getDocsProof());
								System.out.println("Guarantee Cover : "
										+ loanApplication
												.getGuaranteeCoverString());
								System.out
										.println("Market value of guarantee cover : "
												+ loanApplication
														.getMarktValOfCover());
								System.out.println("Application status : "
										+ loanApplication.getStatus());
								System.out.println("Interview Date : "
										+ loanApplication.getInterviewDate());
								System.out
										.println("----------------------------------------------------------");

							}

						} else {
							System.out.println("End of list!!");
						}

						System.out.println("Want to continue(Y/N)");
						String input = scanner.next();
						while (input.equalsIgnoreCase("Y")) {
							if (loanProcessingService.viewApplicationList() != null) {
								System.out.println();
								System.out.println("Enter the application ID:");
								int applicationId = scanner.nextInt();
								System.out
										.println("Select the operation to be performed: ");
								System.out.println("1. Accept");
								System.out.println("2. Approve");
								System.out.println("3. Reject");
								int opt = scanner.nextInt();
								int update = loanApprovalDepartmentService
										.changeStatus(opt, applicationId);
								if (update == 0) {
									System.out
											.println("Status updated successfully!!!");
								} else {
									System.out
											.println("Error encountered. Please Try again later!");
								}
							}

						}

						break;

					default:
						System.out.println("You have successfully exited!!");
						System.exit(0);
					}

				} while (true);

			}

		}

	}

	// to get valid option from the user

	public static int getValidUserInput() {
		while (!scanner.hasNextInt()) {
			System.out.println("==============Invalid Choice==============");
			System.out.print("Enter Valid Choice : ");
			scanner.next();
		}
		int option = scanner.nextInt();
		scanner.nextLine();
		return option;
	}

	/*
	 * This function will be called by main and will return a validated bean
	 * object OR null if details are invalid
	 */

	private static LoanProgramsOfferedBean populateLoanProgramsOfferedBean() {
		loanProgramsOffered = new LoanProgramsOfferedBean();
		System.out.println("_________________________________________");
		System.out.println();
		System.out.println("-------NEW LOAN PROGRAM---------");
		System.out.println();
		System.out.println("Enter Loan Program name:");
		loanProgramsOffered.setLoanProgramName(scanner.next());
		System.out.println("Enter Loan Description:");
		scanner.nextLine();
		loanProgramsOffered.setDescription(scanner.nextLine());
		System.out.println("Enter loan type:");
		loanProgramsOffered.setLoanType(scanner.next());
		System.out.println("Enter duration in years:");
		loanProgramsOffered.setDurationInYears(scanner.nextInt());
		System.out.println("Enter minimum loan amount:");
		loanProgramsOffered.setMinLoanAmnt(scanner.nextDouble());
		System.out.println("Enter maximum loan amount:");
		loanProgramsOffered.setMaxLoanAmnt(scanner.nextDouble());
		System.out.println("Enter rate of interest:");
		loanProgramsOffered.setRateOfIntrest(scanner.nextDouble());
		System.out.println("Enter proofs required:");
		loanProgramsOffered.setProofReq(scanner.next());
		return loanProgramsOffered;
	}

	/*
	 * This function will be called by main and will return a validated bean
	 * object OR null if details are invalid
	 */
	private static CustomerBean populateCustomerBean() {
		// Reading and setting the values for the customerBean

		System.out.println();
		System.out.println("Enter your personal details!!");
		System.out.println();
		System.out.println("Enter application ID: ");
		customer.setApplicationId(scanner.nextInt());
		System.out.println("Enter name: ");
		customer.setApplicantName(scanner.next());
		System.out.println("Enter Date of Birth (DD-MM-YYYY) : ");
		customer.setDateOfBirth(scanner.next());
		System.out.println("Enter marital status (Married/Unmarried) : ");
		customer.setMaritalStatus(scanner.next());
		System.out.println("Enter Phone Number:");
		customer.setPhoneNumber(scanner.nextLong());
		System.out.println("Enter mobile number:");
		customer.setMobileNumber(scanner.nextLong());
		System.out.println("Enter number of dependents:");
		customer.setDependentsCount(scanner.nextInt());
		System.out.println("Enter Email ID:");
		customer.setEmailId(scanner.next());
		return customer;
	}

	/*
	 * This function will be called by main and will return a validated bean
	 * object OR null if details are invalid
	 */

	private static LoanApplicationBean populateLoanApplicationBean() {

		// Reading and setting the values for the loanApplicationBean
		loanApplication = new LoanApplicationBean();
		System.out.println("_________________________________________");
		System.out.println();
		System.out.println("-------APPLICATION FORM--------");
		System.out.println();
		System.out.println("Enter the Loan Program name:");
		loanApplication.setLoanProgram(scanner.next());
		System.out.println("Enter the loan amount:");
		loanApplication.setLoanAmount(scanner.nextDouble());
		System.out.println("Enter address of property:");
		loanApplication.setPropertyAddress(scanner.next());
		System.out.println("Enter Annual family income:");
		loanApplication.setAnnualFamilyIncome(scanner.nextDouble());
		System.out.println("Enter the document proofs:");
		loanApplication.setDocsProof(scanner.next());
		System.out.println("Gaurantee cover details:");
		loanApplication.setGuaranteeCoverString(scanner.next());
		System.out.println("Market value of gaurantee cover:");
		loanApplication.setMarktValOfCover(scanner.nextDouble());

		return loanApplication;
	}

	// display loans programs offered
	private void displayLoanPrograms() {
		LoanProcessingServiceImpl loanProcessingService = new LoanProcessingServiceImpl();
		ArrayList<LoanProgramsOfferedBean> loanProgramsOfferedList = new ArrayList<LoanProgramsOfferedBean>();
		loanProgramsOfferedList = loanProcessingService.display();
		if (loanProgramsOfferedList != null) {
			Iterator<LoanProgramsOfferedBean> i = loanProgramsOfferedList
					.iterator();
			while (i.hasNext()) {
				loanProgramsOffered = i.next();
				System.out.println("Loan program name : "
						+ loanProgramsOffered.getLoanProgramName());
				System.out.println("Description : "
						+ loanProgramsOffered.getDescription());
				System.out.println("Loan Type : "
						+ loanProgramsOffered.getLoanType());
				System.out.println("Duration (years) : "
						+ loanProgramsOffered.getDurationInYears());
				System.out.println("Minimum loan amount : "
						+ loanProgramsOffered.getMinLoanAmnt());
				System.out.println("Maximum loan amount : "
						+ loanProgramsOffered.getRateOfIntrest());
				System.out.println("Proofs required : "
						+ loanProgramsOffered.getProofReq());
				System.out
						.println("----------------------------------------------------------");

			}

		} else {
			System.out.println("End of list!!");
		}

	}

}
