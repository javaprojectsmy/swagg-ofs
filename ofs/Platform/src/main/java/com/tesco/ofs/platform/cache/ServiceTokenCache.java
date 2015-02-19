package com.tesco.ofs.platform.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.tesco.ofs.platform.security.authentication.Authenticator;

public class ServiceTokenCache{
	
	private int maxSize = 100;	  	
	private final LoadingCache<String, Object> cache;	
	private static ServiceTokenCache serviceTokenCache;	
	  
	private ServiceTokenCache(Authenticator authenticator, int tokenExpiryDuration) {		
		
	    cache = CacheBuilder.newBuilder().recordStats().
	    		          maximumSize( maxSize ).
	    		          expireAfterWrite(tokenExpiryDuration, TimeUnit.SECONDS).
	    		          build( new CacheLoader<String,Object>() {
						        @Override
						        public Object load( String key ) throws Exception {
						        	
						          return getToken(key, authenticator);
						        }
	      }
	    );	    
	 }
		  
	public static  ServiceTokenCache getInstance(Authenticator authenticator, int tokenExpiryDuration)
	{			 		  		  
	  if(serviceTokenCache == null)
		 synchronized(ServiceTokenCache.class)
		 {
			 if (serviceTokenCache == null)
				 serviceTokenCache = new ServiceTokenCache(authenticator, tokenExpiryDuration);
		 }
	  
	  return serviceTokenCache;
    }
		 
	public Object getTokenFromCache(String token ) {
		return cache.getUnchecked( token );
	}	 	
		  	
	private Object getToken(String key, Authenticator authenticator) 
	{
	  return getTokenFromProvider(key, authenticator);
			  
	}
		  
	private Object getTokenFromProvider(String key, Authenticator authenticator)
	{				
		
		
		return authenticator.getServiceToken(key);		
		
	 }
	  	 	 		  
}
