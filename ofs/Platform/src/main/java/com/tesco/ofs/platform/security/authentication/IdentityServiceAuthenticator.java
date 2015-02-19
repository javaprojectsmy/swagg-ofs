package com.tesco.ofs.platform.security.authentication;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tesco.ofs.platform.trace.exception.OFSPlatformRunTimeException;
import com.tesco.ofs.platform.trace.logger.OFSPlatformLogger;

@SuppressWarnings("deprecation")
public class IdentityServiceAuthenticator implements Authenticator{
	
	private static final OFSPlatformLogger logger = OFSPlatformLogger.getLogger(IdentityServiceAuthenticator.class);
	
	private String identityServiceHost;
	private String identityServiceName;
	
	
	public IdentityServiceAuthenticator(String url, String serviceName)
	{
		this.identityServiceHost = url;
		this.identityServiceName = serviceName;
		
	}

	@Override
	public ServiceToken getServiceToken(String key) 
	{
		ServiceToken stoken = null;
		
		String resourceurl = identityServiceHost + "/" + identityServiceName + "?access_token=" + key;
		//String status="";
	
		logger.info("Calling identity service to validate the token:::::" + resourceurl);
	    @SuppressWarnings({"resource", "deprecation" })
		HttpClient client = new DefaultHttpClient();		                      
	    HttpGet get = new HttpGet(resourceurl); 	    
	    
	    try
	    {
		    HttpResponse response = client.execute(get);    		    			    
		    if(response.getEntity() != null)
		    {		    			        		      	    
		    	//JSON response to object mapping start
		    	ObjectMapper mapper = new ObjectMapper();		    	
				stoken = mapper.readValue(new InputStreamReader(response.getEntity().getContent()), ServiceToken.class);
				 			  		     			    	
		    }
		    else
		    {
		    	stoken = new ServiceToken();
		    	stoken.setStatus("INVALID");
		    }
	    } catch (IllegalStateException | IOException e)
	    {
	    	throw new OFSPlatformRunTimeException(e.getMessage(), e);
	    }
	    
	    return stoken;
		
	}

}
