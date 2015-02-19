package com.tesco.ofs.platform.mediation.transformation;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.tesco.ofs.platform.trace.exception.OFSPlatformRunTimeException;
import com.tesco.ofs.platform.trace.exception.handler.ThrowableExceptionHandler;

public class OFSPlatformTransformationUtil implements OFSPlatformConstants{
	

		@SuppressWarnings("unchecked")
		private static <T> T json2JaxbObject(String in, T t) throws IOException {
		    ObjectMapper mapper = new ObjectMapper();	   
		    AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(
		        mapper.getTypeFactory());
		    mapper.setAnnotationIntrospector(introspector);
		    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		    T root = (T) mapper.readValue(in, t.getClass());
		    return root;
		  }
		
		/**
	     * Returns object representation of json input
	     *
	     * @param jsonInput String representing the JSON input
	     * @param t class representing the json input
	     * @return <tt>T</tt>Object representing JSON input
	     */  
		public static <T> T json2Object(String in, T t) throws IOException {
		    ObjectMapper mapper = new ObjectMapper();	   		    
		    T root = (T) mapper.readValue(in, t.getClass());
		    return root;
		  }

		/**
	     * Returns XML string representation of the json input.
	     * This method doesn't validate the xml against XML XSD
	     *
	     * @param jsonInput String representing the JSON input
	     * @param t JAXB class representation of the XML
	     * @return <tt>String</tt> XML String representation of JSON input
	     */   
		  public static <T> String json2XML(String jsonInput, T t) {
			  
			  
			  
			  StringWriter writer = new StringWriter();
			  
			  try
			  {
				  T root = (T) json2JaxbObject(jsonInput, t);
				  JAXBContext context = JAXBContext.newInstance(t.getClass());            
				  Marshaller m = context.createMarshaller();
				  m.marshal(root, writer);
			  } catch(IOException | JAXBException ex)			  
			  {
				  ThrowableExceptionHandler eh = new ThrowableExceptionHandler();
				  eh.throwRunTimeException(ex.getMessage(), ex);
			  }
			  
			  return writer.toString();		 
		  }
		  
		  	/**
		     * Returns XML string representation of the json input.
		     * This method doesn't validate the xml against XML XSD
		     *
		     * @param jsonInput Object representing the JSON input
		     * @param t JAXB class representation of the XML
		     * @return <tt>String</tt> XML String representation of JSON input
		     */   
			  public static <T> String json2XML(T t) throws IOException, JAXBException {
				  
				  //T root = (T) json2JaxbObject(jsonInput, t);
				  
				  StringWriter writer = new StringWriter();
				  JAXBContext context = JAXBContext.newInstance(t.getClass());            
				  Marshaller m = context.createMarshaller();
				  m.marshal(t, writer);
				  
				  return writer.toString();		 
			  }		  
		   
		  	/**
		     * Returns XML string representation of the json input.
		     * This method validates the xml against XML XSD
		     *
		     * @param jsonInput String representing the JSON input
		     * @param t JAXB class representation of the XML
		     * @param xsdFile XSD File (.xsd)
		     * @return <tt>String</tt> XML String representation of JSON input
		     */  
		  public static <T> String json2XML(String jsonInput, T t, File xsdFile) throws Exception{
			  
			  T root = (T) json2JaxbObject(jsonInput, t);
			  
			  StringWriter writer = new StringWriter();
			  JAXBContext context = JAXBContext.newInstance(t.getClass());  		  
			  JAXBSource source = new JAXBSource(context, t); 
			  OFSPlatformXMLUtil.validateJAXBObjectModelAgainstXSD(source, xsdFile);
			  
			  Marshaller m = context.createMarshaller();
			  m.marshal(root, writer);
			  
			  return writer.toString();		 
		  }
	 
		  	/**
		     * Returns XML string representation of the json input.
		     * This method validates the xml against XML XSD
		     *
		     * @param jsonInput String representing the JSON input
		     * @param t JAXB class representation of the XML
		     * @param xsd XSD in String format
		     * @return <tt>String</tt> XML String representation of JSON input
		  	 * @throws IOException 
		  	 * @throws JAXBException 
		     */  
		  public static <T> String json2XML(String jsonInput, T t, String xsd) throws IOException, JAXBException {
		  
			  T root = (T) json2JaxbObject(jsonInput, t);
			  
			  StringWriter writer = new StringWriter();
			  JAXBContext context = JAXBContext.newInstance(t.getClass());  		  
			  JAXBSource source = new JAXBSource(context, t); 
			  OFSPlatformXMLUtil.validateJAXBObjectModelAgainstXSD(source, xsd);
			  
			  Marshaller m = context.createMarshaller();
			  m.marshal(root, writer);
			  
			  return writer.toString();		 
		  }
		  
		  /**
		     * Returns json string representation of XML String.
		     * This method validates the xml against XML XSD
		     *
		     * @param XML String representing the JSON input
		     * @param t JAXB class representation of the XML
		     * @return <tt>String</tt> JSON String representation of the XML
		     */  
		  @SuppressWarnings("unchecked")
			public static <T> String xml2Json(String xmlString, T t) throws IOException, JAXBException, XMLStreamException {		  		
				  		 
				  Reader stringReader = new StringReader(xmlString);
				  XMLStreamReader xmlReader=XMLInputFactory.newInstance().createXMLStreamReader(stringReader);
				  				 
				  JAXBContext context = JAXBContext.newInstance(t.getClass());            
				  Unmarshaller unmarshaller = context.createUnmarshaller();
				  T obj = (T) unmarshaller.unmarshal(xmlReader, t.getClass());		  		  
				  
				  String json = object2Json(obj);
						  
				  return json;		 
			  }
		  
		  	/**
		     * Converts an object into JSON String
		     *
		     * @param t Object representation of JSON
		     * @param t class representing the JSON input
		     * @return <tt>String</tt> JSON String
		     */  
		  public static <T> String object2Json(T t) {
			  	String jsonString = "";
			    ObjectMapper mapper = new ObjectMapper();
			    AnnotationIntrospector inspector = new JacksonAnnotationIntrospector();
			    mapper.setAnnotationIntrospector(inspector);
			    try
			    {
			    	jsonString =  mapper.writeValueAsString(t);
			    }
			    catch(IOException ioe)
			    {
			    	throw new OFSPlatformRunTimeException("error occured during object to json conversion", ioe);
			    }
			    
			    return jsonString;
			  }
		  
		 public static String xmlToJson(String xmlString) throws JSONException
			{
				 
				JSONObject xmlJSONObj = XML.toJSONObject(xmlString); 
				           
				String jsonString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR); 
			            
				return jsonString; 			        
				
			}
		 
		 /**
		     * Converts an object into xml string String
		     *
		     * @param source Object representation of JSON
		     * @param type class 
		     * @return <tt>String</tt> XML String
		     */  
		 public static String objectToXml(Object source, Class type) throws JAXBException 
		 {
		        String xml;
		        StringWriter writer = new StringWriter();
		       
		        JAXBContext context = JAXBContext.newInstance(type);
		        Marshaller marshaller = context.createMarshaller();
		        marshaller.marshal(source, writer);
		        xml = writer.toString();
		        
		        return xml;
	    }		 
		  
		  //test method
		  /*public static void main(String[] args) throws IOException, JAXBException {
				
				BufferedReader br = new BufferedReader(new FileReader("F:/Platform/APILayer/PlatformApi/Order-Json.txt"));
				
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();
		        
		        while (line != null) {
		            sb.append(line);
		            sb.append(System.lineSeparator());
		            line = br.readLine();
		        }
		        
		        br.close();
		        System.out.println("line:::::" + sb.toString());
			      String outXml = json2XML(sb.toString(), new Order()); //convert object to xml string
			      System.out.println("outXml:::::" + outXml);
		}*/


}
