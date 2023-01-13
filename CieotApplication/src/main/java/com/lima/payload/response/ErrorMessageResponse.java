package com.lima.payload.response;

public class ErrorMessageResponse {
	private int errCode;
	private String errMessage;

	public ErrorMessageResponse(int errCode, String errMessage) {
		super();
		this.errCode = errCode;
		this.errMessage = errMessage;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

}
