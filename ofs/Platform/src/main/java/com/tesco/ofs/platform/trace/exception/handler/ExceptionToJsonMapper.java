package com.tesco.ofs.platform.trace.exception.handler;

import com.tesco.ofs.platform.mediation.transformation.OFSPlatformTransformationUtil;
import com.tesco.ofs.platform.trace.exception.OFSPlatformException;

public class ExceptionToJsonMapper implements IExceptionMapperHandler {
	
	@Override
	public String mapException(OFSPlatformException ex) {
		// TODO Auto-generated method stub		
		String jsonError = "";
		jsonError = OFSPlatformTransformationUtil.object2Json(ex);
		
		return jsonError;
		
	}

}
