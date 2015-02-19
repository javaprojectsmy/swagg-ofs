package com.tesco.ofs.platform.trace.exception.handler;

import com.tesco.ofs.platform.trace.exception.OFSPlatformException;

public interface IExceptionMapperHandler {
	
	public String mapException(OFSPlatformException ex);

}
