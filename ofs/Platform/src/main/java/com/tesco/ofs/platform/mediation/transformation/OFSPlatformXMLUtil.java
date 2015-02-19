package com.tesco.ofs.platform.mediation.transformation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.tesco.ofs.platform.trace.exception.OFSPlatformRunTimeException;
import com.tesco.ofs.platform.trace.exception.handler.ExceptionHandlerFactory;
import com.tesco.ofs.platform.trace.exception.handler.IThrowableExceptionHandler;
import com.tesco.ofs.platform.trace.exception.handler.ThrowableExceptionHandler;

public class OFSPlatformXMLUtil {
	
	public static  void validateJAXBObjectModelAgainstXSD(JAXBSource source, String xsd) 
	{		
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);			
	        Schema schema = null;
	        
	        IThrowableExceptionHandler eh = new ThrowableExceptionHandler();
	        
			try {
				schema = factory.newSchema(new StreamSource(new ByteArrayInputStream(xsd.getBytes())));
			} catch (SAXException e) {
				/*// TODO Auto-generated catch block
				e.printStackTrace();*/							
				eh.throwRunTimeException(e.getMessage(), e);
			}
	        Validator validator = schema.newValidator();
	        //validator.setErrorHandler(new XMLValidationErrorHandler());
	        try {
				validator.validate(source);
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				eh.throwRunTimeException(e.getMessage(), e);
			}									 
	 }
	
	public static  void validateJAXBObjectModelAgainstXSD(JAXBSource source, File  xsdFile)
	 {
		
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = null;
			 IThrowableExceptionHandler eh = new ThrowableExceptionHandler();
			try {
				schema = factory.newSchema(xsdFile);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				eh.throwRunTimeException(e.getMessage(), e);
			}
			Validator validator = schema.newValidator();
			//validator.setErrorHandler(new XMLValidationErrorHandler());
			try {
				validator.validate(source);
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				eh.throwRunTimeException(e.getMessage(), e);
			}
		
	 }
	
	public static Document createDocument(String xmlString)
	{
		DocumentBuilder db = null;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new OFSPlatformRunTimeException("Exception Parsing the error XML", e);
		}
		Document doc = null;
		try {
			doc = db.parse(new InputSource(new StringReader(xmlString)));
		} catch (SAXException | IOException e) {
			throw new OFSPlatformRunTimeException("Exception Parsing the error XML", e);
		}
		
		return doc;
		
	}
		

}
