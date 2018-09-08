package com.cg.laps.bean;

public class LoanProgramsOfferedBean {
	private String loanProgramName;
	private String description;
	private String loanType;
	private int durationInYears;
	private double minLoanAmnt;
	private double maxLoanAmnt;
	private double rateOfIntrest;
	private String proofReq;

	public String getLoanProgramName() {
		return loanProgramName;
	}

	public void setLoanProgramName(String loanProgramName) {
		this.loanProgramName = loanProgramName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getDurationInYears() {
		return durationInYears;
	}

	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
	}

	public double getMinLoanAmnt() {
		return minLoanAmnt;
	}

	public void setMinLoanAmnt(double minLoanAmnt) {
		this.minLoanAmnt = minLoanAmnt;
	}

	public double getMaxLoanAmnt() {
		return maxLoanAmnt;
	}

	public void setMaxLoanAmnt(double maxLoanAmnt) {
		this.maxLoanAmnt = maxLoanAmnt;
	}

	public double getRateOfIntrest() {
		return rateOfIntrest;
	}

	public void setRateOfIntrest(double rateOfIntrest) {
		this.rateOfIntrest = rateOfIntrest;
	}

	public String getProofReq() {
		return proofReq;
	}

	public void setProofReq(String proofReq) {
		this.proofReq = proofReq;
	}

	public LoanProgramsOfferedBean() {

	}

	public LoanProgramsOfferedBean(String loanProgramString,
			String description, String loanType, int durationInYears,
			int minLoanAmnt, int maxLoanAmnt, double rateOfIntrest,
			String proofReq) {
		super();
		this.loanProgramName = loanProgramString;
		this.description = description;
		this.loanType = loanType;
		this.durationInYears = durationInYears;
		this.minLoanAmnt = minLoanAmnt;
		this.maxLoanAmnt = maxLoanAmnt;
		this.rateOfIntrest = rateOfIntrest;
		this.proofReq = proofReq;
	}

	@Override
	public String toString() {
		return "LoanProgramOfferedBean [loanProgramString=" + loanProgramName
				+ ", description=" + description + ", loanType=" + loanType
				+ ", durationInYears=" + durationInYears + ", minLoanAmnt="
				+ minLoanAmnt + ", maxLoanAmnt=" + maxLoanAmnt
				+ ", rateOfIntrest=" + rateOfIntrest + ", proofReq=" + proofReq
				+ "]";
	}

}
