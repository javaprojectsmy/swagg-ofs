package com.tesco.ofs.order.appconfig;

/*
 * This is Dropwizard application's configuration class.
 * When your application runs Configured Commands like the server command, 
 * Dropwizard parses the provided YAML configuration file and builds an instance of this class 
 * by mapping YAML field names to object field names.
 *  
 *  
*/


import io.dropwizard.Configuration;

public class FulfillmentOrderConfiguration extends Configuration  {

    private String serviceTokenExpiryTime;    
    private String enableAuthentication;         
    private String identitySvcHost;
    private String identityResource;
       
	public String getServiceTokenExpiryTime() { return serviceTokenExpiryTime; }
    public String getEnableAuthentication() { return enableAuthentication; }    
    public String getIdentitySvcHost() { return identitySvcHost; }
    public String getIdentityResource() { return identityResource; }

}
