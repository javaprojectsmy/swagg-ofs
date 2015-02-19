package com.tesco.ofs.platform.security.authentication;

public interface Authenticator{
	
	public static final String SSL_PROPERTY = "javax.net.ssl.trustStore";
	
	public Object getServiceToken(String key);
	public default void setSSLCertificateInSystemProperty(String certificate)
	{
		
		System.setProperty(SSL_PROPERTY, certificate);
	}

}
