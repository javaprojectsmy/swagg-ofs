package com.tesco.ofs.platform.mediation.transformation;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLValidationErrorHandler implements ErrorHandler {
	
	
	@Override
	public void error(SAXParseException spex) throws SAXException {
		
		//log.error(spex.getLocalizedMessage());
		
	}

	@Override
	public void fatalError(SAXParseException spex) throws SAXException {
		
		//log.error(spex.getLocalizedMessage());
		
	}

	@Override
	public void warning(SAXParseException spex) throws SAXException {
		
		//log.warn(spex.getLocalizedMessage());
		
	}

}
