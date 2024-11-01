package com.fsuite.BgvApi.exception;

public class AppException extends RuntimeException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String ERR_IN_FETCHING_CANDIDATE_LIST = "ERR_IN_FETCHING_CANDIDATE_LIST";
	public static final String ERR_IN_FETCHING_CANDIDATE_ADDRESS_DETAILS = "ERR_IN_FETCHING_CANDIDATE_ADDRESS_DETAILS";
	public static final String ERR_IN_FETCHING_CANDIDATE_EDUCATION_DETAILS="ERR_IN_FETCHING_CANDIDATE_EDUCATION_DETAILS";
	public static final String ERR_IN_FETCHING_CANDIDATE_WORK_DETAILS="ERR_IN_FETCHING_CANDIDATE_WORK_DETAILS";

	public static final String ERR_IN_INSERTING_CANDIDATE_ADDRESS_DETAILS = "ERR_IN_INSERTING_CANDIDATE_ADDRESS_DETAILS";
	public static final String ERR_IN_INSERTING_CANDIDATE_DETAILS = "ERR_IN_INSERTING_CANDIDATE_DETAILS";
	public static final String ERR_IN_INSERTING_CANDIDATE_EDUCATION_DETAILS = "ERR_IN_INSERTING_CANDIDATE_EDUCATION_DETAILS";
	public static final String ERR_IN_INSERTING_CANDIDATE_WORK_EXPERIENCE_DETAILS = "ERR_IN_INSERTING_CANDIDATE_WORK_EXPERIENCE_DETAILS";

	public static final String ERR_IN_UPDATING_CANDIDATE_STATUS = "ERR_IN_UPDATING_CANDIDATE_STATUS";
	
	private String errCode;
	private String errMsg;
	private Throwable cause;

	public AppException(String errCode, String errMsg, Throwable cause)
	{
		super(errCode,cause);
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.cause = cause;
	}

	public AppException(String errCode, String errMsg)
	{
		super(errCode);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode()
	{
		return this.errCode;
	}
	public String getErrMsg()
	{
		return this.errMsg;
	}
	@Override
	public Throwable getCause()
	{
		return this.cause;
	}


}
