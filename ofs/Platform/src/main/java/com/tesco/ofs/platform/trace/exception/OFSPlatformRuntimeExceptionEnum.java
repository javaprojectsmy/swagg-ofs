package com.tesco.ofs.platform.trace.exception;

public enum OFSPlatformRuntimeExceptionEnum implements TescoExceptionType {
	
	INTERNAL_SERVER_ERROR("InternalServerError", "500"), 
	API_ERROR("APIError", "501"),
	NOT_IMPLEMENTED("APIError", "502");
	
	private final String errorType;
	private final String errorCode;
	 
	  private OFSPlatformRuntimeExceptionEnum(String errorType, String errorCode) {
	    this.errorType = errorType;
	    this.errorCode = errorCode;
	  }


	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return this.errorCode;
	}

	@Override
	public String getErrorType() {
		// TODO Auto-generated method stub
		return this.errorType;
	}

}
