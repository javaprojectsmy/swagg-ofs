package com.tesco.ofs.platform.mediation.protocoladpter.httpsoap;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;


public class SoapClient implements ISoapClient {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("/Mediation_Sync_Context.xml");

    // send & receive soap request/response to the configured default URI
    public String soapSendAndReceive(String message) {
    	WebServiceTemplate soapServiceTemplate = context.getBean("soapServiceTemplate", WebServiceTemplate.class);
        StreamSource source = new StreamSource(new StringReader(message));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(os);
        soapServiceTemplate.sendSourceAndReceiveToResult(source,  result);
        return String.valueOf(os.toByteArray());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
    	
    	/*Properties systemProps = System.getProperties();
    	systemProps.put("http.proxySet", "true");
    	systemProps.put("http.proxyHost", "dev01isa01.dotcom.tesco.org");
    	systemProps.put("http.proxyPort", "8080");
    	*/
    	
    	new SoapClient().soapSendAndReceive("");
     }
    
}
