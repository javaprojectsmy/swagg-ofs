package com.tesco.ofs.platform.security.authentication;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.server.Request;

import com.google.common.base.Strings;
import com.tesco.ofs.platform.cache.ServiceTokenCache;
import com.tesco.ofs.platform.trace.exception.OFSPlatformException;
import com.tesco.ofs.platform.trace.exception.OFSPlatformExceptionEnum;
import com.tesco.ofs.platform.trace.exception.TescoExceptionType;
import com.tesco.ofs.platform.trace.exception.handler.ExceptionToJsonMapper;
import com.tesco.ofs.platform.trace.exception.handler.IExceptionMapperHandler;
import com.tesco.ofs.platform.trace.logger.OFSPlatformLogger;

public class SecurityFilter implements Filter {
	
	private static final OFSPlatformLogger logger = OFSPlatformLogger.getLogger(SecurityFilter.class);
	
	private int accessTokenExpiryDuration = 3600;
	private String identitySvcHost;
	private String sslCertificate;
	private String identityResource;
	
	public String getSslCertificate() {
		return sslCertificate;
	}

	public void setSslCertificate(String sslCertificate) {
		this.sslCertificate = sslCertificate;
	}

	public SecurityFilter(int accessTokenExpiryDuration, String identitySvcHost, String identityResource)
	{
		this.accessTokenExpiryDuration = accessTokenExpiryDuration;
		this.identitySvcHost = identitySvcHost;
		this.identityResource = identityResource;
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
				
		//1. Get the access token from the http authorization Header
		String serviceToken = ((Request)request).getHeader("Authorization");
		String clientid = ((Request)request).getHeader("clientid");
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		logger.debug("client id and service token in request header:::::" + clientid + "::" + serviceToken);
		
		//2. Validate the token from cache or Identity Service	
		
        if(Strings.isNullOrEmpty(serviceToken) || Strings.isNullOrEmpty(clientid) ){          	
        	String errorJson = getTokenValidationError(OFSPlatformExceptionEnum.MISSING_AUTH_TOKEN, "Auth token or client id is  missing in the request");    		    		
        	httpServletResponse.setContentType(MediaType.APPLICATION_JSON);
        	httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);        	
        	httpServletResponse.getOutputStream().print(errorJson);
        } else if (!isValidServiceToken(clientid, serviceToken)){         	
        	String errorJson = getTokenValidationError(OFSPlatformExceptionEnum.UNAUTHORIZED, "Unauthoized Access");    		    		
        	httpServletResponse.setContentType(MediaType.APPLICATION_JSON);
        	httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);        	        	
        	httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	httpServletResponse.getOutputStream().print(errorJson);
        	
        } else {
        	
            filterChain.doFilter(request, response);
        }              
									
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}	
	
	 private boolean isValidServiceToken(String clientid, String token)
	 {
		 Authenticator authenticator = new IdentityServiceAuthenticator(identitySvcHost, identityResource);
		 ServiceTokenCache tokenValidator = ServiceTokenCache.getInstance(authenticator, accessTokenExpiryDuration);
		 String clientIdFromToken = "";
		 
	    	logger.debug("token expires in:::" + accessTokenExpiryDuration);
	    	
    		ServiceToken stoken = (ServiceToken)tokenValidator.getTokenFromCache(token);
    		
    		Collection<Claims> claims = stoken.getClaims();
		    
    		 if(claims != null)
 		    {
    			Iterator<Claims> it = claims.iterator();
		    
		   
			    while(it.hasNext())
			    {
			    	Claims clm = it.next();
			    	if(clm.getClaimType().equalsIgnoreCase("http://schemas.tesco.com/ws/2011/12/identity/claims/clientid"))
			    	{
			    		clientIdFromToken = clm.getValue();
			    	    break;
			    	}			    	    			    	
			    }
			    	    	
	    		logger.debug("ServiceTokenValidator -> ServiceToken: " + stoken);
		        
		        if (stoken.getStatus().equalsIgnoreCase("VALID") && clientIdFromToken.equalsIgnoreCase(clientid))
		        {		        			        	
		        	return true;
		        	
		        }
		    }
	    	
	    	return false;
	    }
	 
	 private String getTokenValidationError(TescoExceptionType type, String message)
	 {
			OFSPlatformException ope = new OFSPlatformException(message);
	     	ope.setErrorCode(type);
	 		ope.setErrorType(type);				
	 		ope.setErrorDescription(message);    		
	 		IExceptionMapperHandler exceptionMapperHandler = new ExceptionToJsonMapper();
	 		String errorJson = exceptionMapperHandler.mapException(ope);
	 		
	 		logger.error("Token validation error::::" + errorJson);
	 		
	 		return errorJson;
		 
	 }
}
